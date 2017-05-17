package com.htsoft.oa.action.document;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.log.Action;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocFolder;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.document.Document;
import com.htsoft.oa.model.document.DocumentFile;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.document.DocFolderService;
import com.htsoft.oa.service.document.DocPrivilegeService;
import com.htsoft.oa.service.document.DocumentService;

/**
 * 
 * @author csx
 * 
 */
public class DocFolderAction extends BaseAction {

	@Resource
	private DocFolderService docFolderService;
	@Resource
	private DocPrivilegeService docPrivilegeService;
	@Resource
	private DocumentService documentService;
	private DocFolder docFolder;

	private Integer folderNum = 0;
	private Integer documentNum = 0;
	private Integer attachsNum = 0;
	private double filesize = 0;

	private Long folderId;

	private static Integer ALL_RIGHT = 7;// 7的二进制为111
	private static Integer NOT_RIGHT = 0;
	private static Long ISPARENT = 0l;

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public DocFolder getDocFolder() {
		return docFolder;
	}

	public void setDocFolder(DocFolder docFolder) {
		this.docFolder = docFolder;
	}

	/**
	 * 显示个人文档树
	 */
	public String list() {
		Long curUserId = ContextUtil.getCurrentUserId();
		List<DocFolder> list = docFolderService.getByUserId(curUserId);
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method,"我的文件夹"), null, false);
		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * @param list
	 * @param method
	 * @param rootName
	 * @return
	 */
	private List<Object> getResult(List<DocFolder> list, String method,String rootName) {
		List<Object> dataList = new ArrayList<Object>();
		if (!StringUtils.isNotEmpty(method)) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", "0");
			dataRecord.put("text", "我的文件夹");
			dataRecord.put("parentId", null);
			dataList.add(dataRecord);

		}
		for (DocFolder docFolder : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", docFolder.getFolderId().toString());
			dataRecord.put("text", docFolder.getFolderName());
			String parentId = docFolder.getParentId().toString();
			if (StringUtils.isNotEmpty(method)
					&& docFolder.getParentId() != null
					&& docFolder.getParentId().longValue() == 0L) {
				parentId = null;
			}
			dataRecord.put("parentId", parentId);
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 知识目录树
	 * 
	 * @return
	 */

	public String tree() {
		List<DocFolder> list = docFolderService.getPublicFolder();
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method,"知识目录"), null, false);

		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 选择树
	 * 
	 * @return
	 */
	public String select() {
		AppUser appUser = ContextUtil.getCurrentUser();
		StringBuffer buff = new StringBuffer("[{id:'" + 0
				+ "',text:'公共文件夹',expanded:true,children:[");
		List<DocFolder> docList = docFolderService
				.getPublicFolderByParentId(0l);// 最顶层父节点
		for (DocFolder docFolder : docList) {
			List<Integer> rights = docPrivilegeService.getRightsByFolder(
					appUser, docFolder.getFolderId());
			Integer right = NOT_RIGHT;
			for (Integer in : rights) {
				right |= in;
			}
			Set<String> roleRight = appUser.getRights();
			if (roleRight.contains("__ALL")) {
				right = ALL_RIGHT;
			}
			if (right == NOT_RIGHT) {
				buff.append("{id:'" + docFolder.getFolderId())
						.append("',disabled:true,text:'"
								+ docFolder.getFolderName())
						.append("',expanded:true,");
				buff.append(findChildsFolderByRight(docFolder.getFolderId(),
						right, false));
			} else {
				buff.append("{id:'" + docFolder.getFolderId())
						.append("',text:'" + docFolder.getFolderName())
						.append("',expanded:true,");
				if (right == ALL_RIGHT) {
					buff.append(findChildsFolderByRight(
							docFolder.getFolderId(), right, true));
				} else {
					buff.append(findChildsFolderByRight(
							docFolder.getFolderId(), right, false));
				}
			}
		}
		if (!docList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	/**
	 * 共享列表
	 * 
	 * @return
	 */

	public String share() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_isShared_SN_EQ", "1");
		List<DocFolder> list = docFolderService.getAll(filter);
		Type type = new TypeToken<List<DocFolder>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}



	/**
	 * 找子文件夹
	 * 
	 * @param parentId
	 * @return
	 */

	public String findChildsFolder(Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = docFolderService
				.getPublicFolderByParentId(parentId);
		if (list.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		} else {
			sb.append("children:[");
			for (DocFolder folder : list) {
				sb.append("{id:'" + folder.getFolderId() + "',text:'"
						+ folder.getFolderName() + "',expanded:true,");
				sb.append(findChildsFolder(folder.getFolderId()));
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]},");
			return sb.toString();
		}

	}

	/**
	 * 通过权限来查找子文件夹
	 * 
	 * @param parentId
	 * @param right
	 * @return
	 */
	public String findChildsFolderByRight(Long parentId, Integer right,
			boolean isAllRight) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = docFolderService
				.getPublicFolderByParentId(parentId);
		if (list.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		} else {
			sb.append("children:[");
			for (DocFolder folder : list) {
				Integer in = right;
				if (isAllRight) {
					in = ALL_RIGHT;
				} else {
					if (in != NOT_RIGHT) {
						in = NOT_RIGHT;
						AppUser appUser = ContextUtil.getCurrentUser();
						List<Integer> rights = docPrivilegeService
								.getRightsByFolder(appUser,
										folder.getFolderId());
						for (Integer inte : rights) {
							in |= inte;
						}
					}
				}
				if (in == NOT_RIGHT) {
					sb.append("{id:'" + folder.getFolderId()
							+ "',disabled:true,text:'" + folder.getFolderName()
							+ "',expanded:true,");
					sb.append(findChildsFolderByRight(folder.getFolderId(), in,
							isAllRight));
				} else {
					sb.append("{id:'" + folder.getFolderId() + "',text:'"
							+ folder.getFolderName() + "',expanded:true,");
					sb.append(findChildsFolderByRight(folder.getFolderId(), in,
							isAllRight));
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]},");
			return sb.toString();
		}

	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				docFolderService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 删除目录
	 * 
	 * @return
	 */
	public String remove() {
		String folderId = getRequest().getParameter("folderId");
		if (StringUtils.isNotEmpty(folderId)) {
			DocFolder tmpFolder = docFolderService.get(new Long(folderId));
			List<DocFolder> docFolderList = docFolderService
					.getFolderLikePath(tmpFolder.getPath());
			// 批量删除其下的目录
			for (DocFolder folder : docFolderList) {
				List<Document> list = documentService.findByFolder(folder
						.getPath());
				if (list.size() > 0) {
					jsonString = "{success:false,message:'该目录下还有文档，请把文件删除后删除该目录'}";
					return SUCCESS;
				}
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_docFolder.folderId_L_EQ", folder
						.getFolderId().toString());
				List<DocPrivilege> priList = docPrivilegeService.getAll(filter);
				for (DocPrivilege dp : priList) {
					docPrivilegeService.remove(dp);
				}
				docFolderService.remove(folder.getFolderId());
			}
		}

		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		DocFolder docFolder = docFolderService.get(folderId);

		Gson gson = new Gson();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(docFolder));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		AppUser appUser = ContextUtil.getCurrentUser();
		docFolder.setAppUser(appUser);
		if (docFolder.getFolderId() == null) {// 添加的操作
			if (docFolder.getIsShared() == null) {
				docFolder.setAppUser(ContextUtil.getCurrentUser());
				docFolder.setIsShared(DocFolder.IS_NOT_SHARED);
			}
			docFolderService.save(docFolder);

			// 保存它的相对路径
			if (docFolder.getParentId() == null || docFolder.getParentId() == 0) {
				docFolder.setPath(docFolder.getFolderId() + ".");
			} else {
				DocFolder pFolder = docFolderService.get(docFolder
						.getParentId());
				if (pFolder != null) {
					docFolder.setPath(pFolder.getPath()
							+ docFolder.getFolderId() + ".");
				}
			}

			docFolderService.save(docFolder);
		} else {
			DocFolder df = docFolderService.get(docFolder.getFolderId());
			df.setDescp(docFolder.getDescp());
			df.setFolderName(docFolder.getFolderName());
			docFolderService.save(df);
		}

		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 文件夹移动
	 * 
	 * @return
	 */
	public String move() {
		String strFolderIdOld = getRequest().getParameter("folderIdOld");
		String strFolderIdNew = getRequest().getParameter("folderIdNew");
		if (StringUtils.isNotEmpty(strFolderIdOld)
				&& StringUtils.isNotEmpty(strFolderIdNew)) {
			Long folderIdOld = new Long(strFolderIdOld);
			Long folderIdNew = new Long(strFolderIdNew);
			String newPath = null;
			DocFolder folderOld = docFolderService.get(folderIdOld);
			DocFolder folderNew = new DocFolder();
			if (folderIdNew > 0) {
				folderNew = docFolderService.get(folderIdNew);
				newPath = folderNew.getPath() + folderIdOld.toString() + ".";
				boolean flag = Pattern.compile(folderOld.getPath())
						.matcher(folderNew.getPath()).find();
				if (flag) {
					setJsonString("{success:false,msg:'不能移到子文件夹下！'}");
					return SUCCESS;
				}
			} else {
				folderIdNew = ISPARENT;
				newPath = folderIdOld.toString() + ".";
			}
			String oldPath = folderOld.getPath();
			folderOld.setParentId(folderIdNew);
			folderOld.setPath(newPath);
			List<DocFolder> list = docFolderService.getFolderLikePath(oldPath);
			for (DocFolder folder : list) {
				folder.setPath(folder.getPath().replaceFirst(oldPath, newPath));
				docFolderService.save(folder);
			}
			docFolderService.save(folderOld);
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false,msg:'请联系系统管理员！'}");
		}
		return SUCCESS;
	}

	@Action(description = "显示文件")
	public String folder() {
		// DocFolder docFolder=docFolderService.get(folderId);
		String isUp = getRequest().getParameter("isUp");
		if (folderId == null) {
			folderId = 0l;// 最上层
		} else {
			if (StringUtils.isNotEmpty(isUp) && "true".equals(isUp)) {
				DocFolder folder = docFolderService.get(folderId);
				if (folder != null) {
					folderId = folder.getParentId();
				}
			}
		}
		List<DocumentFile> lists = new ArrayList<DocumentFile>();
		String isSearch = getRequest().getParameter("isSearch");
		List<DocFolder> list = new ArrayList<DocFolder>();
		List<Document> documents = new ArrayList<Document>();
		if (StringUtils.isNotEmpty(isSearch) && "true".equals(isSearch)) {
			String fileName = getRequest().getParameter("fileName");
			Long userId = ContextUtil.getCurrentUserId();
			list = docFolderService.findByUserAndName(userId, fileName);
			Document doc = new Document();
			doc.setDocName(fileName);
			documents = documentService.findByPersonal(userId, doc, null, null,
					null);
		} else {
			list = docFolderService.getUserFolderByParentId(
					ContextUtil.getCurrentUserId(), folderId);
			documents = documentService.findByFolder(folderId);
		}
		for (DocFolder folder : list) {
			DocumentFile file = new DocumentFile();
			file.setFileId(folder.getFolderId());
			file.setFileName(folder.getFolderName());
			file.setFileSize("0 bytes");
			file.setFileType("目录");
			file.setParentId(folder.getParentId());
			DocFolder fol = docFolderService.get(folder.getParentId());
			if (fol != null) {
				file.setParentName(fol.getFolderName());
			}
			file.setIsFolder(DocumentFile.IS_FOLDER);
			lists.add(file);
		}
		for (Document doc : documents) {
			DocumentFile file = new DocumentFile();
			file.setFileId(doc.getDocId());
			file.setFileName(doc.getDocName());
			file.setFileSize(doc.getContent().getBytes() + " bytes");
			file.setFileType("文档");
			file.setIsFolder(DocumentFile.NOT_FOLDER);
			file.setIsShared(doc.getIsShared());
			file.setAuthor(doc.getAuthor());
			file.setUpdateTime(doc.getUpdatetime());
			file.setKeywords(doc.getKeywords());
			lists.add(file);
		}

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(lists, lists.size());

		return SUCCESS;
	}

	/**
	 * 明细，或者属性
	 * 
	 * @return
	 */
	public String detail() {
		String fileId = getRequest().getParameter("fileId");
		String isPersonal = getRequest().getParameter("isPersonal");
		String isFolder = getRequest().getParameter("isFolder");
		StringBuffer buff = new StringBuffer("{success:true,");
		if (StringUtils.isNotEmpty(fileId) && StringUtils.isNotEmpty(isFolder)) {
			if ("true".equals(isFolder)) {
				if ("0".equals(fileId)) {
					String path = "/";
					buff.append("fileId:").append(0).append(",fileName:'")
							.append("我的文件夹").append("'");
					buff.append(",descp:'").append("根目录").append("'");
					buff.append(",path:'" + path + "'");
					buff.append(",fileType:'目录'");
					DocFolder docFolder = new DocFolder();
					docFolder.setFolderId(0l);
					boolean isPer = false;
					if (StringUtils.isNotEmpty(isPersonal)
							&& "true".equals(isPersonal)) {
						isPer = true;
					}
					sumNum(docFolder, isPer);
				} else {
					DocFolder docFolder = docFolderService
							.get(new Long(fileId));
					String path = "";
					if (docFolder.getParentId() != null
							&& docFolder.getParentId() != 0) {
						path = findPath(docFolder.getParentId(), path);
					}
					buff.append("fileId:").append(docFolder.getFolderId())
							.append(",fileName:'")
							.append(docFolder.getFolderName()).append("'");
					buff.append(",descp:'").append(docFolder.getDescp())
							.append("'");
					buff.append(",path:'" + path + "'");
					buff.append(",fileType:'目录'");
					if (docFolder != null) {
						sumNum(docFolder, false);
					}
				}
			} else {
				Document document = documentService.get(new Long(fileId));
				String path = "";
				path = findPath(document.getFolderId(), path);
				buff.append("fileId:").append(document.getDocId())
						.append(",fileName:'").append(document.getDocName())
						.append("'");
				buff.append(",fileType:'" + document.getDocType() + "'")
						.append(",author:'").append(document.getAuthor())
						.append("',keywords:'").append(document.getKeywords())
						.append("'").append(",path:'").append(path).append("'");
				Set<FileAttach> fileAttachs = document.getAttachFiles();
				attachsNum = attachsNum + fileAttachs.size();
				filesize = filesize + fileSize(document);
			}

		}
		logger.info("folderNum:" + folderNum + "documentNum:" + documentNum
				+ "attachsNum:" + attachsNum);
		buff.append(",folderNum:" + folderNum + ",documentNum:" + documentNum
				+ ",attachsNum:" + attachsNum + ",docFileSize:'"
				+ getStrFileSize(filesize) + "'");
		buff.append("}");
		setJsonString(buff.toString());
		logger.info(buff.toString());
		return SUCCESS;
	}

	/**
	 * 查找当前路径
	 * 
	 * @param folderId
	 * @param path
	 * @return
	 */
	private String findPath(Long folderId, String path) {
		DocFolder df = docFolderService.get(folderId);
		if (df != null) {
			path = "/" + df.getFolderName() + path;
			return findPath(df.getParentId(), path);
		} else {
			return path;
		}
	}

	private void sumNum(DocFolder docFolder, boolean isPer) {
		List<Document> list = documentService.findByFolder(docFolder
				.getFolderId());
		for (Document doc : list) {
			Set<FileAttach> fileAttachs = doc.getAttachFiles();
			attachsNum = attachsNum + fileAttachs.size();
			documentNum++;
			filesize = filesize + fileSize(doc);
		}
		List<DocFolder> folders = new ArrayList<DocFolder>();
		if (!isPer) {
			folders = docFolderService.findByParentId(docFolder.getFolderId());
		} else {
			folders = docFolderService.getUserFolderByParentId(
					ContextUtil.getCurrentUserId(), docFolder.getFolderId());
		}
		for (DocFolder folder : folders) {
			folderNum++;
			sumNum(folder, false);
		}
	}

	public void childList(List<DocumentFile> lists, List<DocFolder> folders,
			AppUser appUser, boolean isEmpty, String fileName) {
		for (DocFolder folder : folders) {
			if (isEmpty
					|| (StringUtils.isNotEmpty(fileName) && folder
							.getFolderName().indexOf(fileName) != -1)) {
				DocumentFile file = new DocumentFile();
				file.setFileId(folder.getFolderId());
				file.setFileName(folder.getFolderName());
				file.setFileType("目录");
				file.setParentId(folder.getParentId());
				DocFolder fol = docFolderService.get(folder.getParentId());
				if (fol != null) {
					file.setParentName(fol.getFolderName());
				}
				file.setIsFolder(DocumentFile.IS_FOLDER);
				lists.add(file);

			}
			List<DocFolder> list = docFolderService
					.getPublicFolderByParentId(folder.getFolderId());
			if (list.size() > 0) {
				childList(lists, list, appUser, isEmpty, fileName);
			}

		}
	}

	public String knowledge() {
		String isSearch = getRequest().getParameter("isSearch");
		AppUser appUser = ContextUtil.getCurrentUser();
		Set<String> roleRight = appUser.getRights();
		boolean isSuperUser = false;
		if (roleRight.contains("__ALL")) {
			isSuperUser = true;
		}
		if (StringUtils.isNotEmpty(isSearch) && "true".equals(isSearch)) {
			String fileName = getRequest().getParameter("fileName");
			String author = getRequest().getParameter("author");
			String keywords = getRequest().getParameter("keywords");
			List<DocumentFile> lists = new ArrayList<DocumentFile>();
			List<DocFolder> list = docFolderService
					.getPublicFolderByParentId(0l);
			Integer right = NOT_RIGHT;
			for (DocFolder folder : list) {
				right = NOT_RIGHT;
				if (isSuperUser) {
					right = ALL_RIGHT;
				} else {
					List<Integer> rights = docPrivilegeService
							.getRightsByFolder(appUser, folder.getFolderId());
					for (Integer in : rights) {
						right |= in;
					}
				}
				if (right != NOT_RIGHT) {
					boolean isEmpty = (StringUtils.isEmpty(fileName)
							&& StringUtils.isEmpty(author) && StringUtils
							.isEmpty(keywords));
					if (isEmpty
							|| (StringUtils.isNotEmpty(fileName) && folder
									.getFolderName().indexOf(fileName) != -1)) {
						DocumentFile file = new DocumentFile();
						file.setFileId(folder.getFolderId());
						file.setFileName(folder.getFolderName());
						file.setFileType("目录");
						file.setParentId(folder.getParentId());
						DocFolder fol = docFolderService.get(folder
								.getParentId());
						if (fol != null) {
							file.setParentName(fol.getFolderName());
						}
						file.setIsFolder(DocumentFile.IS_FOLDER);
						if (isSuperUser)
							lists.add(file);
					}
					List<DocFolder> listss = new ArrayList<DocFolder>();
					if (isSuperUser)
						listss = docFolderService
								.getPublicFolderByParentId(folder.getFolderId());
					// List<DocFolder>
					// listss=docFolderService.getPublicFolderByParentId(folder.getFolderId());
					if (listss.size() > 0) {
						childList(lists, listss, appUser, isEmpty, fileName);
					}
				}
			}
			PagingBean pb = getInitPagingBean();
			pb.setPageSize(10000);
			Document doc1 = new Document();
			doc1.setDocName(fileName);
			doc1.setAuthor(author);
			doc1.setKeywords(keywords);
			List<Document> docs = documentService.findByPublic(null, doc1,
					null, null, appUser, pb);

			for (Document doc : docs) {
				short rightD = 0;
				short rightM = 0;
				short rightR = 0;
				if (isSuperUser) {
					rightD = 1;
					rightM = 1;
					rightR = 1;
				} else {
					Long folderId = doc.getFolderId();
					right = NOT_RIGHT;
					if (folderId != 0l) {
						List<Integer> folderrights = docPrivilegeService
								.getRightsByFolder(appUser, folderId);
						for (Integer in : folderrights) {
							right |= in;
						}
						right = rightOfFolder(appUser, right, folderId);
					}
					if (right != NOT_RIGHT) {
						String strRight = Integer.toBinaryString(right);
						char[] cc = strRight.toCharArray();
						if (cc.length == 2) {
							if (cc[0] == '1') {
								rightM = 1;
							}
						}
						if (cc.length == 3) {
							if (cc[0] == '1') {
								rightD = 1;
							}
							if (cc[1] == '1') {
								rightM = 1;
							}
						}
						rightR = 1;
					}
				}
				if (rightR > 0) {
					DocumentFile file = new DocumentFile();
					file.setFileId(doc.getDocId());
					file.setFileName(doc.getDocName());
					file.setFileSize(getStrFileSize(fileSize(doc)));
					file.setFileType("文档");
					file.setIsFolder(DocumentFile.NOT_FOLDER);
					file.setIsShared(doc.getIsShared());
					file.setRightRead(rightR);
					file.setRightMod(rightM);
					file.setAuthor(doc.getAuthor());
					file.setKeywords(doc.getKeywords());
					file.setUpdateTime(doc.getUpdatetime());
					file.setRightDel(rightD);
					lists.add(file);
				}
			}

			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toPageJson(lists, pb.getTotalItems());
			return SUCCESS;
		}
		String isUp = getRequest().getParameter("isUp");// //////

		Integer right = NOT_RIGHT;
		if (folderId == null) {
			folderId = 0l;// 最上层
		} else {
			if (StringUtils.isNotEmpty(isUp) && "true".equals(isUp)) {
				DocFolder folder = docFolderService.get(folderId);
				if (folder != null) {
					folderId = folder.getParentId();
				}
			}
		}

		short rightD = 0;
		short rightM = 0;
		short rightR = 0;
		if (isSuperUser) {
			rightD = 1;
			rightM = 1;
			rightR = 1;

		} else {
			if (folderId != 0l) {
				List<Integer> folderrights = docPrivilegeService
						.getRightsByFolder(appUser, folderId);
				for (Integer in : folderrights) {
					right |= in;
				}
				right = rightOfFolder(appUser, right, folderId);
			}
			if (right != NOT_RIGHT) {
				String strRight = Integer.toBinaryString(right);
				char[] cc = strRight.toCharArray();
				if (cc.length == 2) {
					if (cc[0] == '1') {
						rightM = 1;
					}
				}
				if (cc.length == 3) {
					if (cc[0] == '1') {
						rightD = 1;
					}
					if (cc[1] == '1') {
						rightM = 1;
					}
				}
				rightR = 1;
			}
		}
		List<DocumentFile> lists = new ArrayList<DocumentFile>();
		List<DocFolder> list = new ArrayList<DocFolder>();
		if (isSuperUser)
			list = docFolderService.getPublicFolderByParentId(folderId);
		List<Document> documents = documentService.findByFolder(folderId);
		for (DocFolder folder : list) {
			if (folder.getParentId() == 0) {
				right = NOT_RIGHT;
				if (isSuperUser) {
					right = ALL_RIGHT;
				} else {
					List<Integer> rights = docPrivilegeService
							.getRightsByFolder(appUser, folder.getFolderId());
					for (Integer in : rights) {
						right |= in;
					}
				}
			} else {
				right = ALL_RIGHT;
			}
			if (right != NOT_RIGHT) {
				DocumentFile file = new DocumentFile();
				file.setFileId(folder.getFolderId());
				file.setFileName(folder.getFolderName());
				file.setFileType("目录");
				file.setParentId(folder.getParentId());
				DocFolder fol = docFolderService.get(folder.getParentId());
				if (fol != null) {
					file.setParentName(fol.getFolderName());
				}
				file.setIsFolder(DocumentFile.IS_FOLDER);
				lists.add(file);
			}
		}
		for (Document doc : documents) {
			DocumentFile file = new DocumentFile();
			file.setFileId(doc.getDocId());
			file.setFileName(doc.getDocName());
			file.setFileSize(getStrFileSize(fileSize(doc)));
			file.setFileType("文档");
			file.setIsFolder(DocumentFile.NOT_FOLDER);
			file.setIsShared(doc.getIsShared());
			file.setRightRead(rightR);
			file.setRightMod(rightM);
			file.setAuthor(doc.getAuthor());
			file.setKeywords(doc.getKeywords());
			file.setUpdateTime(doc.getUpdatetime());
			file.setRightDel(rightD);
			lists.add(file);
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(lists, lists.size());
		return SUCCESS;
	}

	private String getStrFileSize(double size) {
		DecimalFormat df = new DecimalFormat("0.00");
		if (size > 1024 * 1024) {
			double ss = size / (1024 * 1024);
			return df.format(ss) + " M";
		} else if (size > 1024) {
			double ss = size / 1024;
			return df.format(ss) + " KB";
		} else {
			return size + " bytes";
		}
	}

	private double fileSize(Document doc) {
		Set<FileAttach> files = doc.getAttachFiles();
		Iterator<FileAttach> it = files.iterator();
		double filesize = 0;
		while (it.hasNext()) {
			FileAttach file = it.next();
			if (file != null && file.getTotalBytes() != null) {
				filesize += file.getTotalBytes();
			}
		}
		String content = doc.getContent();
		if (StringUtils.isNotEmpty(content)) {
			int size = content.getBytes().length;
			filesize += size;
		}
		return filesize;
	}

	public Integer rightOfFolder(AppUser appUser, Integer right, Long folderId) {
		DocFolder docFolder = docFolderService.get(folderId);
		if (docFolder != null) {
			List<Integer> folderrights = docPrivilegeService.getRightsByFolder(
					appUser, docFolder.getFolderId());
			for (Integer in : folderrights) {
				right |= in;
			}
			// Integer
			// rights=rightOfFolder(appUser,right,docFolder.getParentId());
			// right;//|=rights;
		}
		return right;
	}

	public String knowledgeTree() {
		AppUser appUser = ContextUtil.getCurrentUser();
		StringBuffer buff = new StringBuffer("[{id:'" + 0
				+ "',text:'知识目录',expanded:true,children:[");
		List<DocFolder> docList = docFolderService
				.getPublicFolderByParentId(0l);// 最顶层父节点
		boolean isFlag = false;
		for (DocFolder docFolder : docList) {
			List<Integer> rights = docPrivilegeService.getRightsByFolder(
					appUser, docFolder.getFolderId());

			Integer right = NOT_RIGHT;
			for (Integer in : rights) {
				right |= in;
			}
			Set<String> roleRight = appUser.getRights();
			if (roleRight.contains("__ALL")) {
				right = ALL_RIGHT;
			}

			if (right != NOT_RIGHT) {
				isFlag = true;
				buff.append("{id:'" + docFolder.getFolderId())
						.append("',text:'" + docFolder.getFolderName())
						.append("',expanded:true,");
				if (right == ALL_RIGHT) {
					buff.append(findChildsFolder(docFolder.getFolderId()));
				} else {
					buff.append(findChildsFolderByRights(
							docFolder.getFolderId(), right, false));
				}
			}
		}
		if (isFlag) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	public String findChildsFolderByRights(Long parentId, Integer right,
			boolean isAllRight) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = docFolderService
				.getPublicFolderByParentId(parentId);
		if (list.size() == 0) {
			sb.append("leaf:true},");
			return sb.toString();
		} else {
			sb.append("children:[");
			boolean flag = false;
			for (DocFolder folder : list) {
				Integer in = right;
				if (isAllRight) {
					in = ALL_RIGHT;
				} else {
					if (in != NOT_RIGHT) {
						in = NOT_RIGHT;
						AppUser appUser = ContextUtil.getCurrentUser();
						List<Integer> rights = docPrivilegeService
								.getRightsByFolder(appUser,
										folder.getFolderId());
						for (Integer inte : rights) {
							in |= inte;
						}
					}
				}
				if (in != NOT_RIGHT) {
					flag = true;
					sb.append("{id:'" + folder.getFolderId() + "',text:'"
							+ folder.getFolderName() + "',expanded:true,");
					sb.append(findChildsFolderByRights(folder.getFolderId(),
							in, isAllRight));
				}
			}
			if (flag) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]},");
			return sb.toString();
		}

	}

	public String onlineTree() {
		AppUser appUser = ContextUtil.getCurrentUser();
		StringBuffer buff = new StringBuffer("[{id:'" + 0
				+ "',text:'在线文档目录',expanded:true,children:[");
		List<DocFolder> docList = docFolderService
				.getOnlineFolderByParentId(0l);// 最顶层父节点
		boolean isFlag = false;
		for (DocFolder docFolder : docList) {
			List<Integer> rights = docPrivilegeService.getRightsByFolder(
					appUser, docFolder.getFolderId());
			Integer right = NOT_RIGHT;
			for (Integer in : rights) {
				right |= in;
			}
			Set<String> roleRight = appUser.getRights();
			if (roleRight.contains("__ALL")) {
				right = ALL_RIGHT;
			}
			if (right != NOT_RIGHT) {
				isFlag = true;
				buff.append("{id:'" + docFolder.getFolderId())
						.append("',text:'" + docFolder.getFolderName())
						.append("',expanded:true,");
				buff.append(findOnlineChildsFolder(docFolder.getFolderId()));
			}
		}
		if (isFlag) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());

		return SUCCESS;
	}

	/**
	 * 找子文件夹
	 * 
	 * @param parentId
	 * @return
	 */

	public String findOnlineChildsFolder(Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = docFolderService
				.getOnlineFolderByParentId(parentId);
		if (list.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		} else {
			sb.append("children:[");
			for (DocFolder folder : list) {
				sb.append("{id:'" + folder.getFolderId() + "',text:'"
						+ folder.getFolderName() + "',expanded:true,");
				sb.append(findOnlineChildsFolder(folder.getFolderId()));
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]},");
			return sb.toString();
		}

	}

	public String onlineList() {
		AppUser appUser = ContextUtil.getCurrentUser();
		Set<String> roleRight = appUser.getRights();
		boolean isSuperUser = false;
		if (roleRight.contains("__ALL")) {
			isSuperUser = true;
		}
		String isSearch = getRequest().getParameter("isSearch");
		if (StringUtils.isNotEmpty(isSearch) && "true".equals(isSearch)) {
			String fileName = getRequest().getParameter("fileName");
			List<DocumentFile> lists = new ArrayList<DocumentFile>();
			List<DocFolder> list = docFolderService
					.getOnlineFolderByParentId(0l);
			Integer right = NOT_RIGHT;
			for (DocFolder folder : list) {
				right = NOT_RIGHT;
				if (isSuperUser) {
					right = ALL_RIGHT;
				} else {
					List<Integer> rights = docPrivilegeService
							.getRightsByFolder(appUser, folder.getFolderId());
					for (Integer in : rights) {
						right |= in;
					}
				}
				if (right != NOT_RIGHT) {
					boolean isEmpty = StringUtils.isEmpty(fileName);
					if (isEmpty
							|| (StringUtils.isNotEmpty(fileName) && folder
									.getFolderName().indexOf(fileName) != -1)) {
						DocumentFile file = new DocumentFile();
						file.setFileId(folder.getFolderId());
						file.setFileName(folder.getFolderName());
						file.setFileType("目录");
						file.setParentId(folder.getParentId());
						DocFolder fol = docFolderService.get(folder
								.getParentId());
						if (fol != null) {
							file.setParentName(fol.getFolderName());
						}
						file.setIsFolder(DocumentFile.IS_FOLDER);
						lists.add(file);
					}
					List<DocFolder> listss = docFolderService
							.getOnlineFolderByParentId(folder.getFolderId());
					if (listss.size() > 0) {
						childOnlineList(lists, listss, appUser, isEmpty,
								fileName);
					}
				}
			}
			Document doc1 = new Document();
			doc1.setDocName(fileName);
			List<Document> docs = documentService.findByOnline(doc1, null,
					null, appUser);

			for (Document doc : docs) {
				short rightD = 0;
				short rightM = 0;
				short rightR = 0;
				if (isSuperUser) {
					rightD = 1;
					rightM = 1;
					rightR = 1;
				} else {
					Long folderId = doc.getFolderId();
					right = NOT_RIGHT;
					if (folderId != 0l) {
						List<Integer> folderrights = docPrivilegeService
								.getRightsByFolder(appUser, folderId);
						for (Integer in : folderrights) {
							right |= in;
						}
						right = rightOfFolder(appUser, right, folderId);
					}
					if (right != NOT_RIGHT) {
						String strRight = Integer.toBinaryString(right);
						char[] cc = strRight.toCharArray();
						if (cc.length == 2) {
							if (cc[0] == '1') {
								rightM = 1;
							}
						}
						if (cc.length == 3) {
							if (cc[0] == '1') {
								rightD = 1;
							}
							if (cc[1] == '1') {
								rightM = 1;
							}
						}
						rightR = 1;
					}
				}
				if (rightR > 0) {
					DocumentFile file = new DocumentFile();
					file.setFileId(doc.getDocId());
					file.setFileName(doc.getDocName());
					file.setFileSize(getStrFileSize(fileSize(doc)));
					file.setFileType(doc.getDocType());
					file.setIsFolder(DocumentFile.NOT_FOLDER);
					file.setIsShared(doc.getIsShared());
					file.setRightRead(rightR);
					file.setRightMod(rightM);
					file.setAuthor(doc.getAuthor());
					file.setKeywords(doc.getKeywords());
					file.setUpdateTime(doc.getUpdatetime());
					file.setRightDel(rightD);
					lists.add(file);
				}
			}
			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toPageJson(lists, lists.size());
			return SUCCESS;
		}

		String isUp = getRequest().getParameter("isUp");

		Integer right = NOT_RIGHT;
		if (folderId == null) {
			folderId = 0l;// 最上层
		} else {
			if (StringUtils.isNotEmpty(isUp) && "true".equals(isUp)) {
				DocFolder folder = docFolderService.get(folderId);
				if (folder != null) {
					folderId = folder.getParentId();
				}
			}
		}

		short rightD = 0;
		short rightM = 0;
		short rightR = 0;
		if (isSuperUser) {
			rightD = 1;
			rightM = 1;
			rightR = 1;

		} else {
			if (folderId != 0l) {
				List<Integer> folderrights = docPrivilegeService
						.getRightsByFolder(appUser, folderId);
				for (Integer in : folderrights) {
					right |= in;
				}
				right = rightOfFolder(appUser, right, folderId);
			}
			if (right != NOT_RIGHT) {
				String strRight = Integer.toBinaryString(right);
				char[] cc = strRight.toCharArray();
				if (cc.length == 2) {
					if (cc[0] == '1') {
						rightM = 1;
					}
				}
				if (cc.length == 3) {
					if (cc[0] == '1') {
						rightD = 1;
					}
					if (cc[1] == '1') {
						rightM = 1;
					}
				}
				rightR = 1;
			}
		}
		List<DocumentFile> lists = new ArrayList<DocumentFile>();
		List<DocFolder> list = docFolderService
				.getOnlineFolderByParentId(folderId);
		List<Document> documents = documentService.findByFolder(folderId);
		for (DocFolder folder : list) {
			if (folder.getParentId() == 0) {
				right = NOT_RIGHT;
				if (isSuperUser) {
					right = ALL_RIGHT;
				} else {
					List<Integer> rights = docPrivilegeService
							.getRightsByFolder(appUser, folder.getFolderId());
					for (Integer in : rights) {
						right |= in;
					}
				}
			} else {
				right = ALL_RIGHT;
			}
			if (right != NOT_RIGHT) {
				DocumentFile file = new DocumentFile();
				file.setFileId(folder.getFolderId());
				file.setFileName(folder.getFolderName());
				file.setFileType("目录");
				file.setParentId(folder.getParentId());
				DocFolder fol = docFolderService.get(folder.getParentId());
				if (fol != null) {
					file.setParentName(fol.getFolderName());
				}
				file.setIsFolder(DocumentFile.IS_FOLDER);
				lists.add(file);
			}
		}
		for (Document doc : documents) {
			DocumentFile file = new DocumentFile();
			file.setFileId(doc.getDocId());
			file.setFileName(doc.getDocName());
			file.setFileSize(getStrFileSize(fileSize(doc)));
			file.setFileType(doc.getDocType());
			file.setIsFolder(DocumentFile.NOT_FOLDER);
			file.setIsShared(doc.getIsShared());
			file.setRightRead(rightR);
			file.setRightMod(rightM);
			file.setAuthor(doc.getAuthor());
			file.setKeywords(doc.getKeywords());
			file.setUpdateTime(doc.getUpdatetime());
			file.setRightDel(rightD);
			lists.add(file);
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(lists, lists.size());
		return SUCCESS;
	}

	public void childOnlineList(List<DocumentFile> lists,
			List<DocFolder> folders, AppUser appUser, boolean isEmpty,
			String fileName) {
		for (DocFolder folder : folders) {
			if (isEmpty
					|| (StringUtils.isNotEmpty(fileName) && folder
							.getFolderName().indexOf(fileName) != -1)) {
				DocumentFile file = new DocumentFile();
				file.setFileId(folder.getFolderId());
				file.setFileName(folder.getFolderName());
				file.setFileType("目录");
				file.setParentId(folder.getParentId());
				DocFolder fol = docFolderService.get(folder.getParentId());
				if (fol != null) {
					file.setParentName(fol.getFolderName());
				}
				file.setIsFolder(DocumentFile.IS_FOLDER);
				lists.add(file);

			}
			List<DocFolder> list = docFolderService
					.getOnlineFolderByParentId(folder.getFolderId());
			if (list.size() > 0) {
				childOnlineList(lists, list, appUser, isEmpty, fileName);
			}

		}
	}
}
