package com.htsoft.core.mail;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.htsoft.core.util.CertUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.util.FilePathUtil;
import com.sun.mail.pop3.POP3Folder;

/**
 * 邮件发送、接收帮助类
 * 
 * @author zxh
 * 
 */
public class MailUtil {
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(MailUtil.class);

	/**
	 * 设置Mail smtp属性
	 * 
	 * @param host
	 *            主机
	 * @param protocol
	 *            协议 一般情况为 smtp
	 * @param fromMail
	 *            发送者邮箱
	 * 
	 * @return
	 */
	private static Properties setProperties(String host, String protocol,
			String fromMail) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.auth", true); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		props.put("mail.from", fromMail);
		return props;

	}

	/**
	 * 简单的邮件发送器
	 * 
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            内容/正文
	 * @param sendTo
	 *            接收者邮件
	 * @param fromMail
	 *            发送者邮件
	 * @param fromName
	 *            发送者名字
	 * @param password
	 *            发送者邮件密码
	 * @param protocol
	 *            协议 一般发送都是“smtp”
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @return
	 */
	public static Boolean sender(String subject, String content, String sendTo,
			String fromMail, String fromName, String mailPassword,
			String protocol, String host, String port) {
		logger.debug("send start...");
		try {
			final String username = fromMail;
			final String pass = mailPassword;
			// 设置属性
			Properties props = setProperties(host, protocol, fromMail);

			// 创建发送器
			JavaMailSenderImpl sender = new JavaMailSenderImpl();

			sender.setHost(host);

			sender.setUsername(username);

			sender.setPassword(pass);

			// 创建消息
			MimeMessage message = sender.createMimeMessage();

			message.addHeader("X-Mailer", "Java Mailer");

			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					false, "UTF-8");

			messageHelper.addTo(sendTo, fromName);

			messageHelper.setFrom(fromMail, fromName);

			messageHelper.setSubject(subject);

			messageHelper.setText(content);

			messageHelper.setSentDate(new Date());

			// 开始发送
			sender.setJavaMailProperties(props);

			sender.send(message);
			logger.debug("send end...");
		} catch (Exception e) {
			System.out.println("Error:" + e);
			logger.info("发送邮件失败");
			return false;
		}
		return true;

	}

	/**
	 * 邮件发送器（带抄送，密送，附件）
	 * 
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param to
	 *            发送邮件地址
	 * @param cc
	 *            抄送邮件地址
	 * @param bcc
	 *            密送邮件地址
	 * @param attachments
	 *            附件，Map<附件路径，附件名称>
	 * @param fromMail
	 *            发送邮箱
	 * @param fromName
	 *            发送者名字
	 * @param password
	 *            密码
	 * @param sendDate
	 *            发送时间
	 * @param protocol
	 *            协议 一般发送都是“smtp”
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sender(String subject, String content,
			InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc,
			Map<String, String> attachments, String fromMail, String fromName,
			String password, Date sendDate, String protocol, String host,
			String port) throws Exception {

		logger.debug("send start...");
		final String username = fromMail;
		final String pass = password;
		// 设置属性
		Properties props = setProperties(host, protocol, fromMail);

		// 创建发送器
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		senderImpl.setHost(host);
		senderImpl.setUsername(username);
		senderImpl.setPassword(pass);

		// 创建消息
		MimeMessage message = senderImpl.createMimeMessage();

		message.addHeader("X-Mailer", "Java Mailer");
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true,
				"UTF-8");

		// 添加发件人
		messageHelper.setFrom(fromMail, fromName);

		// 添加收件人
		if (to != null && to.length > 0)
			messageHelper.setTo(to);
		// 添加抄送人
		if (cc != null && cc.length > 0)
			messageHelper.setCc(cc);
		// 添加暗抄人
		if (bcc != null && bcc.length > 0)
			messageHelper.setBcc(bcc);

		// 主题
		messageHelper.setSubject(subject);
		// 内容 true 表示启动HTML格式的邮件
		messageHelper.setText(content, true);

		messageHelper.setSentDate(sendDate == null ? new Date() : sendDate);

		// 添加附件
		if (null != attachments) {
			for (Iterator<Map.Entry<String, String>> it = attachments
					.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String fileName = entry.getValue();
				String filePath = entry.getKey();
				if (null == fileName || null == filePath) {
					throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("附件" + filePath + "不存在！");
				}

				FileSystemResource fileResource = new FileSystemResource(file);
				messageHelper.addAttachment(
						MimeUtility.encodeText(fileName, "utf-8", "Q"),
						fileResource);
			}
		}
		// 添加验证
		MyAuthenticator auth = new MyAuthenticator(username, pass);

		Session session = Session.getDefaultInstance(props, auth);
		senderImpl.setSession(session);
		// 开始发送
		senderImpl.setJavaMailProperties(props);

		senderImpl.send(message);
		logger.debug("send end...");
	}

	/**
	 * 简单的测试接收邮件
	 * 
	 * @param popHost
	 *            主机
	 * @param popPort
	 *            接口
	 * @param mailAddress
	 *            邮件地址
	 * @param password
	 *            密码
	 * @throws Exception
	 */
	public static Boolean fetch(String popHost, String popPort,
			final String mailAddress, final String password) {
		logger.debug("fectch start...");
		Store store = null;
		POP3Folder inbox = null;
		try {
			// 安装证书
			File certpop = CertUtil.get(popHost, Integer.parseInt(popPort));

			// 设置属性
			Properties props = new Properties();

			props.setProperty("mail.pop3.socketFactory.fallback", "false");
			props.setProperty("mail.pop3.port", popPort);
			props.setProperty("mail.pop3.socketFactory.port", popPort);

			if (certpop != null) {
				logger.debug("ssl connection...");
				Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
				props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
				System.setProperty("javax.net.ssl.trustStore",
						certpop.getAbsolutePath());
				props.setProperty("javax.net.ssl.trustStore",
						certpop.getAbsolutePath());// 证书
			} else {
				final String TLS_FACTORY = "javax.net.SocketFactory";
				props.setProperty("mail.smtp.socketFactory.class", TLS_FACTORY);

				logger.debug("plaintext connection or tls connection...");
				// props.put("mail.smtp.starttls.enable", "true");
			}

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailAddress, password);
				}
			});
			// 请将红色部分对应替换成你的邮箱帐号和密码
			URLName urln = new URLName("pop3", popHost,
					Integer.parseInt(popPort), null, mailAddress, password);

			store = session.getStore(urln);
			store.connect();
			inbox = (POP3Folder) store.getFolder("INBOX");// 主文件夹
			inbox.open(Folder.READ_ONLY);// 只读打开
			// FetchProfile profile = new FetchProfile();//感兴趣的信息
			// profile.add(FetchProfile.Item.ENVELOPE);
			// profile.add(UIDFolder.FetchProfileItem.UID);//邮件标识id
			Message[] messages = inbox.getMessages();
			// inbox.fetch(messages, profile);
			// 邮箱中已下载的邮件uid

			// 邮件列表
			int count = messages.length;
			logger.debug("mail count:" + count);
			logger.debug("fectch end...");
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.info("连接pop 服务器失败");
			return false;
		} finally {
			try {
				if (inbox != null)
					inbox.close(false);
			} catch (Exception e) {
				logger.info("关闭连接失败");
				e.printStackTrace();
			}
			try {
				if (store != null)
					store.close();
			} catch (Exception e) {
				logger.info("关闭连接失败");
				e.printStackTrace();
			}

		}
	}

	public static List<Map<String, Object>> fetch(String popHost,
			String popPort, final String mailAddress, final String password,
			Map<String, String> uidMail, String userName) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		logger.debug("fectch start...");
		// 设置属性
		Properties props = new Properties();
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		props.setProperty("mail.pop3.port", popPort);
		props.setProperty("mail.pop3.socketFactory.port", popPort);

		// 安装证书
		// 以下步骤跟一般的JavaMail操作相同
		File cert = CertUtil.get(popHost, Integer.parseInt(popPort));

		if (cert != null) {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			logger.debug("ssl connection...");
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
			System.setProperty("javax.net.ssl.trustStore",
					cert.getAbsolutePath());
			props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());// 证书
		} else {
			final String TLS_FACTORY = "javax.net.SocketFactory";
			props.setProperty("mail.smtp.socketFactory.class", TLS_FACTORY);

			logger.debug("plaintext connection or tls connection...");
			// props.put("mail.smtp.starttls.enable", "true");
		}

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailAddress, password);
			}
		});
		// 请将红色部分对应替换成你的邮箱帐号和密码
		URLName urln = new URLName("pop3", popHost, Integer.parseInt(popPort),
				null, mailAddress, mailAddress);

		Store store = null;
		POP3Folder inbox = null;

		try {
			store = session.getStore(urln);
			store.connect();
			inbox = (POP3Folder) store.getFolder("INBOX");// 主文件夹
			inbox.open(Folder.READ_ONLY);// 只读打开
			FetchProfile profile = new FetchProfile();// 感兴趣的信息
			// profile.add(FetchProfile.Item.ENVELOPE);
			profile.add(UIDFolder.FetchProfileItem.UID);// 邮件标识id
			Message[] messages = inbox.getMessages();
			inbox.fetch(messages, profile);

			// 邮件列表
			int count = messages.length;
			logger.debug("mail counts :	" + count);
			for (int i = 0; i < count; i++) {
				try {
					Message message = messages[i];
					// 邮箱中已下载的邮件uid
					String uid = inbox.getUID(message);
					if (uidMail.get(uid) == null) {// 判断是否已下载该uid
						Map<String, Object> map = new HashMap<String, Object>();
						try {
							logger.debug("");
							logger.debug("开始接收邮件:	" + (message.getSubject()));
							// 邮件发送者
							String from = (message.getFrom()[0].toString());
							InternetAddress ia = new InternetAddress(from);
							String senerAddress = ia.getAddress();
							if (senerAddress == null || senerAddress.equals("")) {
								senerAddress = " ";
							}
							// 邮件发送者姓名
							String senderName = ia.getPersonal();
							
							if (StringUtils.isEmpty(senderName)) {
								senderName = " ";
							}

							// 接受者
							InternetAddress[] ia_re = null;
							try {
								ia_re = (InternetAddress[]) message
										.getRecipients(Message.RecipientType.TO);
							} catch (javax.mail.internet.AddressException e) {
								e.printStackTrace();
							}
							String re_a = "		";
							String re_n = "		";

							if (ia_re != null && ia_re.length > 0) {

								for (int k = 0; k < ia_re.length; k++) {
									re_a += ia_re[k].getAddress() + ",";
									if (ia_re[k].getPersonal() != null
											&& !ia_re[k].getPersonal().equals(
													"")) {
										re_n += ia_re[k].getPersonal() + ",";
									} else {
										re_n += ia_re[k].getAddress() + ",";
									}
								}

								if (re_a != null && re_a.length() > 1) {
									re_a = re_a.substring(0, re_a.length() - 1);
								}
								if (re_n != null && re_n.length() > 1) {
									re_n = re_n.substring(0, re_n.length() - 1);
								}

							}

							// 抄送者
							InternetAddress[] ia_cc = null;
							try {
								ia_cc = (InternetAddress[]) message
										.getRecipients(Message.RecipientType.CC);
							} catch (javax.mail.internet.AddressException e) {
								e.printStackTrace();
							}
							String cc_a = "	";
							String cc_n = "	";
							if (ia_cc != null && ia_cc.length > 0) {

								for (int k = 0; k < ia_cc.length; k++) {
									cc_a += ia_cc[k].getAddress() + ",";
									cc_n += ia_cc[k].getPersonal() + ",";
								}
								if (cc_a != null && cc_a.length() > 1) {
									cc_a = cc_a.substring(0, cc_a.length() - 1);
								}
								if (cc_n != null && cc_n.length() > 1) {
									cc_n = cc_n.substring(0, cc_n.length() - 1);
								}

							}

							// 暗送者
							InternetAddress[] ia_bcc = null;
							try {
								ia_bcc = (InternetAddress[]) message
										.getRecipients(Message.RecipientType.BCC);
							} catch (javax.mail.internet.AddressException e) {
								e.printStackTrace();
							}
							String bcc_a = "	";
							String bcc_n = "	";
							if (ia_bcc != null && ia_bcc.length > 0) {

								for (int k = 0; k < ia_bcc.length; k++) {
									bcc_a += ia_bcc[k].getAddress() + ",";
									bcc_n += ia_bcc[k].getPersonal() + ",";
								}
								if (bcc_a != null && bcc_a.length() > 1) {
									bcc_a = bcc_a.substring(0,
											bcc_a.length() - 1);
								}
								if (bcc_n != null && bcc_n.length() > 1) {
									bcc_n = bcc_n.substring(0,
											bcc_n.length() - 1);
								}

							}
							// 邮件标题

							String subject = message.getSubject();

							// 邮件发送时间
							Date sentDate = null;
							if (message.getSentDate() != null) {
								sentDate = message.getSentDate();
							} else {
								sentDate = new Date();
							}

							// 邮件正文
							String content = getMailContent(message, userName);
							if (content == null || content.equals("")) {
								content = "	";
							}

							// 附件
							Set<FileAttach> mailFiles = saveFileForFetch(message);

							map.put("uid", uid);
							map.put("senerAddress", senerAddress);
							map.put("senderName", senderName);
							map.put("receiverAddresses", re_a);
							map.put("receiverNames", re_n);
							map.put("ccAddresses", cc_a);
							map.put("ccNames", cc_n);
							map.put("bccAddresses", bcc_a);
							map.put("bccNames", bcc_n);
							map.put("subject", subject);
							map.put("sentDate", sentDate);
							map.put("content", content);
							map.put("mailFiles", mailFiles);
							list.add(map);
							logger.debug("接收邮件成功:	" + (message.getSubject()));
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							System.gc();
						}

					}
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}

			}

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				inbox.close(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				store.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 取得邮件正文
	 * 
	 * @throws MessagingException
	 * @throws IOException
	 * 
	 */
	private static String getMailContent(Part part, String userName) {

		StringBuffer sb = new StringBuffer();
		sb.append(new String(""));
		try {

			/** 纯文本或者html格式的,可以直接解析掉 */
			if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
				sb.append(part.getContent());
			} else if (part.isMimeType("multipart/*")) {
				/*** 可供选择的,一般情况下第一个是plain,第二个是html格式的 */
				if (part.isMimeType("multipart/alternative")) {
					Multipart mp = (Multipart) part.getContent();
					int index = 0;// 兼容不正确的格式,返回第一个部分
					if (mp.getCount() > 1)
						index = 1;// 第2个部分为html格式的哦~
					/** 已经根据情况进行了判断,就算不符合标准格式也不怕了. */
					Part tmp = mp.getBodyPart(index);
					sb.append(tmp.getContent());
				} else if (part.isMimeType("multipart/related")) {
					/** related格式的,那么第一个部分包含了body,里面含有内嵌的内容的链接. */
					Multipart mp = (Multipart) part.getContent();
					Part tmp = mp.getBodyPart(0);
					String body = getMailContent(tmp, userName);
					int count = mp.getCount();
					/** 要把那些可能的内嵌对象都先读出来放在服务器上,然后在替换相对地址为绝对地址 */
					for (int k = 1; count > 1 && k < count; k++) {
						Part att = mp.getBodyPart(k);
						String attname = att.getFileName();
						if (attname != null)
							attname = MimeUtility.decodeText(attname);
						else
							attname = "	";
						try {
							File attFile = new File(
									FilePathUtil.FILE_PATH_ROOT,
									userName.concat(attname));
							attFile.createNewFile();
							FileOutputStream fileoutput = new FileOutputStream(
									attFile);
							InputStream is = att.getInputStream();
							BufferedOutputStream outs = new BufferedOutputStream(
									fileoutput);
							byte b[] = new byte[att.getSize()];
							is.read(b);
							outs.write(b);
							outs.close();
						} catch (Exception e) {
							logger.error("Error occurred when to get the photos from server");
							// e.printStackTrace();
						}
						String content_id[] = att.getHeader("Content-ID");
						if (content_id != null && content_id.length > 0) {
							String cid_name = content_id[0].replaceAll("<", "")
									.replaceAll(">", "");
							body = body.replaceAll("cid:" + cid_name,
									FilePathUtil.FILE_PATH_ROOT.concat("/")
											.concat(userName.concat(attname)));
						}
					}

					sb.append(body);
					return sb.toString();
				} else {
					/** 其他multipart/*格式的如mixed格式,那么第一个部分包含了body,用递归解析第一个部分就可以了 */
					Part tmp = ((Multipart) part.getContent()).getBodyPart(0);
					return getMailContent(tmp, userName);
				}
			} else if (part.isMimeType("message/rfc822")) {
				return getMailContent((Message) part.getContent(), userName);
			} else {
				/** 否则的话,死马当成活马医,直接解析第一部分, */
				Object obj = part.getContent();
				if (obj instanceof String) {
					sb.append(obj);
				} else {
					Multipart mp = (Multipart) obj;
					Part tmp = mp.getBodyPart(0);
					return getMailContent(tmp, userName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "解析正文错误!";
		}
		return sb.toString();
	}

	/**
	 * 取得邮件附件
	 * 
	 * @param multipart
	 *            获取邮件的内容,
	 * 
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private static Set<FileAttach> saveFileForFetch(Message msg)
			throws IOException, MessagingException {
		String contentType = msg.getContentType();
		if (contentType.startsWith("multipart/mixed")) {// 如果为一个集合包
			java.text.SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			// 单个邮件
			Set<FileAttach> mailFiles = new HashSet<FileAttach>();
			// getContent() 是获取包裹内容, Part 相当于外包装
			Multipart multipart = (Multipart) msg.getContent();//
			// 就一个大包裹, 包含所有邮件内容(正文+附件)
			// 依次处理各个部分
			for (int j = 0, n = multipart.getCount(); j < n; j++) {
				Part part = multipart.getBodyPart(j);// 解包, 取出 MultiPart的各个部分,
				// 每部分可能是邮件内容,
				// 也可能是另一个小包裹(MultipPart)
				// 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type:
				// multipart/alternative
				// Content-Disposition: attachment;
				String disposition = part.getDisposition();// 处理是否为附件信息
				if (disposition != null && disposition.equals(Part.ATTACHMENT)) {
					FileAttach fileAttach = new FileAttach();
					fileAttach.setCreatetime(new Date());
					fileAttach.setCreator(ContextUtil.getCurrentUser()
							.getFullname());
					String fileName = part.getFileName();
					if (fileName != null) {
						// 解决文件名乱码问题
						fileName = MimeUtility.decodeText(new String(fileName
								.getBytes("ISO-8859-1"), "gb2312"));
					} else {
						fileName = "	";
					}
					String[] ext = fileName.split("\\.");
					fileAttach.setFileName(fileName);
					fileAttach.setExt(ext[ext.length - 1]);
					fileAttach.setFileType("communicate/outmail/download");
					fileAttach.setNote(String.valueOf(part.getSize())
							+ " bytes");
					fileAttach.setDelFlag(FileAttach.FLAG_NOT_DEL);
					// fileAttach.setTotalBytes(new Double(part.getSize()));
					String ym = df.format(new Date());
					java.io.InputStream in = part.getInputStream();// 打开附件的输入流
					// 读取附件字节并存储到文件中
					String fp_p = FilePathUtil.FILE_PATH_ROOT;// 父目录
					String fp_c = "communicate\\outmail\\download\\"
							+ ContextUtil.getCurrentUser().getUsername() + "\\"
							+ ym + "\\";// 子目录
					fp_p = fp_p.replace("\\", "/");
					fp_c = fp_c.replace("\\", "/");

					String filePath = FileUtil.generateFilename(fileAttach
							.getFileName());
					filePath = filePath.substring(filePath.indexOf("/") + 1,
							filePath.length());

					fileAttach.setFilePath(fp_c + filePath.trim());
					java.io.File f = new java.io.File(fp_p, fp_c);// 创建目录
					if (!f.exists()) {
						if (!f.mkdirs())
							logger.info("目录不存在，创建失败！");
					}

					String f_full_p = fp_p + fileAttach.getFilePath();// 完全路径
					f_full_p = f_full_p.replace("\\", "/");

					java.io.FileOutputStream out = new FileOutputStream(
							f_full_p);
					int data;
					while ((data = in.read()) != -1) {
						out.write(data);
					}
					in.close();
					out.close();
					mailFiles.add(fileAttach);
					logger.debug("附件:" + fileAttach.getFileName() + ","
							+ fileAttach.getFilePath());
				}
			}

			return mailFiles;
		} else {
			return null;
		}
	}

	/**
	 * 将地地址址转化为 可输送的网络地址
	 */
	public static InternetAddress[] getAddressByType(List<EmailAddress> list)
			throws Exception {
		if (list != null) {
			InternetAddress address[] = new InternetAddress[list.size()];
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).toInternetAddress() != null) {
					address[i] = list.get(i).toInternetAddress();
				}
			}
			return address;
		}
		return null;
	}

	/**
	 * 将接收到的字符串,分解出址字符串,以逗号隔开
	 */
	public static String getAddressesToStr(String str) {
		String address = "";
		if (str != null && str.length() > 0 && str.indexOf(";") >= 0) {// 判断不为空,并有多个别名:receives.indexOf(";")>0
			String[] emails = str.split(";");// 拆分每个地填
			for (int i = 0; i < emails.length; i++) {//
				address += parseStr(emails[i],1);
			}
		} else {// 一个地址
			address = parseStr(str,1);
		}
		// 去最后面的逗号,
		if (address != null && address.length() > 1) {
			address = address.substring(0, address.length() - 1);
		}
		return address;
	}


	/**
	 * 将接收到的字附串,分解别名字附串,以逗号隔开
	 */
	public static String getNamesToStr(String str) {
		String name = "";
		if (str != null && str.length() > 0 && str.indexOf(";") >= 0) {// 判断不为空,并有多个别名:receives.indexOf(";")>0
			String[] emails = str.split(";");// 拆分每个地填
			for (int i = 0; i < emails.length; i++) {//
				name += parseStr(emails[i],0);
			}
		} else {// 一个地址
			name += parseStr(str,0);
		}
		if (name != null && name.length() > 1) {
			name = name.substring(0, name.length() - 1);
		}
		if (name == null)
			name = " ";
		return name;
	}

	/**
	 * 转换字符串
	 * @param str
	 * @param b
	 * @return
	 */
	private static String parseStr(String str,int b) {
		String address = "";
		if (str != null && str.indexOf("<") >= 0 && str.indexOf(">") > 0) {// 有别名
			String email[] = str.split("<");
			if(b == 0){
				address += email[0] + ",";
			}else{
				address += email[b].substring(0, email[b].length() - 1) + ",";
			}
		
		} else {// 无别名
			address += str + ",";
		}
		return address;
	}

	
	/**
	 * 将以逗号分隔的地址与别名,组成 List<EmailAddress> 返回
	 * 
	 * @param addresses
	 *            逗号分隔的地址
	 * @param names
	 *            别名
	 * @return List<EmailAddress>
	 */
	public static List<EmailAddress> getEMailStrToList(String addresses,
			String names) {
		List<EmailAddress> list = new ArrayList<EmailAddress>();

		// 将发送人字附串,以逗号隔开,组成list
		if (addresses != null && addresses.length() > 1) {// 判断抄送地址不为空,才进行拆分
			String[] revice_a = addresses.split(",");// 拆分地址
			if (names != null && names.length() > 1) {// 判断地址别名不为空,才拆分
				String[] revice_n = names.split(",");
				// 拆分别名
				for (int i = 0; i < revice_a.length; i++) {
					if (revice_a[i] != null && revice_a[i].length() > 1
							&& revice_a[i].indexOf("@") > 0) {
						if (revice_n.length > i) {// 判断长度足够长,
							EmailAddress add = new EmailAddress(
									revice_a[i].trim(), revice_n[i]);
							list.add(add);
						} else {// 否则给默认默认
							EmailAddress add = new EmailAddress(
									revice_a[i].trim(), revice_a[i].trim());
							list.add(add);
						}
					} else {
						logger.info("收件人地址有误!");
						// throw new RuntimeException("收件人地址有误!");
					}
				}

			} else {// 没有别名
				for (int i = 0; i < revice_a.length; i++) {// 只盾环地址
					if (revice_a[i] != null && revice_a[i].length() > 1
							&& revice_a[i].indexOf("@") > 0) {
						EmailAddress add = new EmailAddress(revice_a[i].trim(),
								revice_a[i].trim());
						list.add(add);
					} else {
						return null;
					}
				}

			}

		} else {
			logger.info("收件人不能为空!");
			// throw new RuntimeException("收件人不能为空!");
		}

		return list;
	}

	/**
	 * 解诀邮件中文乱码问题
	 * 
	 * @param text
	 * @return
	 */
	public static String decodeText(String text) {
		try {
			if (text == null)
				return "";
			if (text.startsWith("=?GB") || text.startsWith("=?gb"))
				text = MimeUtility.decodeText(text);
			else if (text.startsWith("=?ISO-8859-1")
					|| text.startsWith("=?iso-8859-1"))
				text = MimeUtility.decodeText(text);
			else
				text = new String(text.getBytes("ISO8859_1"));
		} catch (Exception e) {
			System.out.println("转换字符编号出错!");
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * 将 转义字符转为转义字符
	 * 
	 */
	public static String changSpecialCharacters(String special) {
		if (special == null) {
			return "";
		} else {
			// special=decodeText(special);
			special = special.replace("\"", "'").replace("\t", "\\t")
					.replace("\n", "\\n").replace(":", "\\:")
					.replace("[", "\\[").replace("]", "\\]")
					.replace("{", "\\{").replace("}", "\\}")
					.replace(",", "\\,").replace("\r", "\\r")
					.replace("null", "");
		}

		return special;
	}

}
