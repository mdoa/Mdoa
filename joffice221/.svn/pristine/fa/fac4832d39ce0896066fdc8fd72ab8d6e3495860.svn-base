package com.htsoft.core.jbpm.jpdl.graph;

import java.util.ArrayList;
import java.util.List;

import com.htsoft.core.util.StringUtil;




/**
 * 流程坐标转换计算类。
 * 
 * @author hotent
 * 
 */
public class TransformUtil {

	public static int Offset = 24;

	public static int minLen = 4;

	/**
	 * 添加函数
	 * 
	 * @param para1
	 * @param para2
	 * @return
	 */
	public static String add(String para1, String para2) {
		float f1 = 0;
		float f2 = 0;
		if (StringUtil.isNotEmpty(para1)) {
			f1 = Float.parseFloat(para1);
		}
		if (StringUtil.isNotEmpty(para2)) {
			f2 = Float.parseFloat(para2);
		}
		f1 = f1 + f2;

		return String.valueOf(f1);
	}

	public static String min(String para1, String para2) {

		float f1 = 0;
		float f2 = 0;
		if (StringUtil.isNotEmpty(para1)) {
			f1 = Float.parseFloat(para1);
		}
		if (StringUtil.isNotEmpty(para2)) {
			f2 = Float.parseFloat(para2);
		}
		if (f1 < f2)
			return String.valueOf(f1);
		else
			return String.valueOf(f2);
	}

	/**
	 * x=1 线条向右 x=0 线条向左 y=0 线条向上 y=1 线条向下 horizontalOffset 为负数向左偏，正数向右偏
	 * verticalOffset 为负数向上偏移，正数向下偏移
	 * 
	 * @param fX
	 * @param fY
	 * @param fW
	 * @param fH
	 * @param fHOffset
	 * @param fVOffset
	 * @param fDirX
	 * @param fDirY
	 * @param tX
	 * @param tY
	 * @param tW
	 * @param tH
	 * @param tHOffset
	 * @param tVOffset
	 * @param tDirX
	 * @param tDirY
	 * @return
	 */
	public static String calc(String fName, String fX, String fY, String fW,
			String fH, String fHOffset, String fVOffset, String fDirX,
			String fDirY, String tName, String tX, String tY, String tW,
			String tH, String tHOffset, String tVOffset, String tDirX,
			String tDirY) {
	
		float fromX = Float.parseFloat(fX);
		float fromY = Float.parseFloat(fY);
		float fromWidth = Float.parseFloat(fW);
		float fromHeight = Float.parseFloat(fH);

		float toX = Float.parseFloat(tX);
		float toY = Float.parseFloat(tY);
		float toWidth = Float.parseFloat(tW);
		float toHeight = Float.parseFloat(tH);

		Shape fromShape = new Shape(fName, fromX, fromY, fromWidth, fromHeight);
		fromShape.setDirectory(fDirX, fDirY);
		fromShape.setHV(fHOffset, fVOffset);

		Shape toShape = new Shape(tName, toX, toY, toWidth, toHeight);
		toShape.setDirectory(tDirX, tDirY);
		toShape.setHV(tHOffset, tVOffset);

		String str = caculate(fromShape, toShape);
		return str;
	}

	/**
	 * x=1 线条向右 x=0 线条向左 y=0 线条向上 y=1 线条向下 horizontalOffset 为负数向左偏，正数向右偏
	 * verticalOffset 为负数向上偏移，正数向下偏移
	 * 
	 * @param fX
	 * @param fY
	 * @param fW
	 * @param fH
	 * @param fHOffset
	 * @param fVOffset
	 * @param fDirX
	 * @param fDirY
	 * @param tX
	 * @param tY
	 * @param tW
	 * @param tH
	 * @param tHOffset
	 * @param tVOffset
	 * @param tDirX
	 * @param tDirY
	 * @return
	 */
	public static String calc_jpdl(String fName, String fX, String fY,
			String fW, String fH, String fHOffset, String fVOffset,
			String fDirX, String fDirY, String tName, String tX, String tY,
			String tW, String tH, String tHOffset, String tVOffset,
			String tDirX, String tDirY) {
		float fromX = Float.parseFloat(fX);
		float fromY = Float.parseFloat(fY);
		float fromWidth = Float.parseFloat(fW);
		float fromHeight = Float.parseFloat(fH);
		
		float toX = Float.parseFloat(tX);
		float toY = Float.parseFloat(tY);
		float toWidth = Float.parseFloat(tW);
		float toHeight = Float.parseFloat(tH);
		
//		System.out.println(fName+"\t"+fromX+","+fromY+":"+fromWidth+","+fromHeight);
//		System.out.println(tName+"\t"+toX+","+toY+":"+toWidth+","+toHeight);
//		
		if(fName.equalsIgnoreCase("bg:StartEvent")||fName.equalsIgnoreCase("bg:EndEvent")){

			fromX-=7;
			fromY-=7;
//			fromWidth+=14;
//			fromHeight-=1;
			fromWidth=50;
			fromHeight=50;
		}

		if(tName.equalsIgnoreCase("bg:StartEvent")||tName.equalsIgnoreCase("bg:EndEvent")){
			toX-=7;
			toY-=7;
//			toWidth+=14;
//			toHeight-=1;
			toWidth=50;
			toHeight=50;
		}
//		System.out.println(fName+"\t"+fromX+","+fromY+":"+fromWidth+","+fromHeight);
//		System.out.println(tName+"\t"+toX+","+toY+":"+toWidth+","+toHeight);
//		
		Shape fromShape = new Shape(fName, fromX, fromY, fromWidth, fromHeight);
		fromShape.setDirectory(fDirX, fDirY);
		fromShape.setHV(fHOffset, fVOffset);

		Shape toShape = new Shape(tName, toX, toY, toWidth, toHeight);
		toShape.setDirectory(tDirX, tDirY);
		toShape.setHV(tHOffset, tVOffset);

		List<Point> points = caculatePoints(fromShape, toShape);
		StringBuffer strbuf=new StringBuffer();
//		for(Point point:points){
//			System.out.println(point.getX()+","+point.getY());
//		}
		
		for(int i=1;i<points.size()-1;i++){
			String str=Math.round(points.get(i).getX())+","+Math.round(points.get(i).getY())+";";
			strbuf.append(str);
			
		}
		if(strbuf.length()>=1){
			strbuf.deleteCharAt(strbuf.length()-1);
		}
		return strbuf.toString();
	}

	/**
	 * 计算xml
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static String caculate(Shape fromShape, Shape toShape) {
		ArrayList<Point> list = null;
		switch (fromShape.getDirectory()) {
		case Top:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println("Top Top");
				list = caculateTopTop(fromShape, toShape);
				break;
			case Left:
				System.out.println("Top Left");
				list = caculateTopLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println("Top Right");
				list = caculateTopRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println("Top Bottom");
				list = caculateTopBottom(fromShape, toShape);
				break;
			}
			break;
		case Left:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println(" Left Top ");
				list = caculateLeftTop(fromShape, toShape);
				break;
			case Left:
				System.out.println(" Left Left ");
				list = caculateLeftLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println(" Left Right ");
				list = caculateLeftRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println(" Left Bottom ");
				list = caculateLeftBottom(fromShape, toShape);
				break;
			}
			break;
		case Right:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println(" Right Top ");
				list = caculateRightTop(fromShape, toShape);
				break;
			case Left:
				System.out.println(" Right Left ");
				list = caculateRightLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println(" Right Right ");
				list = caculateRightRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println(" Right Bottom ");
				list = caculateRightBottom(fromShape, toShape);
				break;
			}
			break;
		case Bottom:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println(" Bottom Top ");
				list = caculateBottomTop(fromShape, toShape);
				break;
			case Left:
				System.out.println(" Bottom Left ");
				list = caculateBottomLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println(" Bottom Right ");
				list = caculateBottomRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println(" Bottom Bottom ");
				list = caculateBottomBottom(fromShape, toShape);
				break;
			}
			break;
		}
		String xml = getPointXml(list);
		return xml;
	}
	
	public static List<Point> caculatePoints(Shape fromShape, Shape toShape) {
		ArrayList<Point> list = null;
		switch (fromShape.getDirectory()) {
		case Top:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println("Top Top");
				list = caculateTopTop(fromShape, toShape);
				break;
			case Left:
				System.out.println("Top Left");
				list = caculateTopLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println("Top Right");
				list = caculateTopRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println("Top Bottom");
				list = caculateTopBottom(fromShape, toShape);
				break;
			}
			break;
		case Left:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println(" Left Top ");
				list = caculateLeftTop(fromShape, toShape);
				break;
			case Left:
				System.out.println(" Left Left ");
				list = caculateLeftLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println(" Left Right ");
				list = caculateLeftRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println(" Left Bottom ");
				list = caculateLeftBottom(fromShape, toShape);
				break;
			}
			break;
		case Right:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println(" Right Top ");
				list = caculateRightTop(fromShape, toShape);
				break;
			case Left:
				System.out.println(" Right Left ");
				list = caculateRightLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println(" Right Right ");
				list = caculateRightRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println(" Right Bottom ");
				list = caculateRightBottom(fromShape, toShape);
				break;
			}
			break;
		case Bottom:
			switch (toShape.getDirectory()) {
			case Top:
				System.out.println(" Bottom Top ");
				list = caculateBottomTop(fromShape, toShape);
				break;
			case Left:
				System.out.println(" Bottom Left ");
				list = caculateBottomLeft(fromShape, toShape);
				break;
			case Right:
				System.out.println(" Bottom Right ");
				list = caculateBottomRight(fromShape, toShape);
				break;
			case Bottom:
				System.out.println(" Bottom Bottom ");
				list = caculateBottomBottom(fromShape, toShape);
				break;
			}
			break;
		}
		return list;
	}
	
	
	/**
	 * TOP TOP 检查完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopTop(Shape fromShape, Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标在源的 上方
		if (toY < fromShape.getBottomCenter().getY() + minLen) {
			// 左右边
			if (toShape.getLeftCenter().getX() > fromX + minLen
					|| toShape.getRightCenter().getX() + minLen < fromX) {
				float tmpy = 0;
				if (toY < fromY) {
					tmpy = toY - Offset;
				} else {
					tmpy = fromY - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				float tmpx = 0;
				float tmpy = (fromY + toShape.getBottomCenter().getY()) / 2;
				if (toX < fromX) {
					tmpx = toShape.getRightCenter().getX() + Offset;
				} else {
					tmpx = toShape.getLeftCenter().getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY - Offset);
				Point p5 = new Point(toX, toY - Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 下方
		else {

			// 左右边
			if (fromShape.getRightCenter().getX() + minLen < toShape
					.getLeftCenter().getX()
					|| fromShape.getLeftCenter().getX() > toShape
							.getRightCenter().getX()) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY - Offset);
				Point p3 = new Point(toX, fromY - Offset);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				float tmpy = (toShape.getBottomCenter().getY() + fromY) / 2;
				float tmpx = 0;
				// 中偏右
				if (fromX < toX) {
					tmpx = fromShape.getRightCenter().getX() + Offset;
				} else {
					tmpx = fromShape.getLeftCenter().getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY - Offset);
				Point p3 = new Point(tmpx, fromY - Offset);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(toX, tmpy);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	/**
	 * top right 检查完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopRight(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		if (fromX >= toX) {
			if (toY + minLen < fromY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {
				if (toX + minLen < fromShape.getLeftCenter().getX()) {
					float tmpx = (fromShape.getLeftCenter().getX() + toX) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				} else {
					float tmpy = 0;
					if (toShape.getTopCenter().getY() < toY) {
						tmpy = toShape.getTopCenter().getY() - Offset;
					} else {
						tmpy = fromY - Offset;
					}
					float tmpx = fromShape.getRightCenter().getX() + Offset;

					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(tmpx, tmpy);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		} else {
			// 上方
			if (toShape.getBottomCenter().getY() + minLen < fromY) {
				float tmpy = (fromY + toShape.getBottomCenter().getY()) / 2;

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX + Offset, tmpy);
				Point p4 = new Point(toX + Offset, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 中部
			else {
				float tmpy = 0;// toY-Offset;
				float tmpx = 0;
				Point p1 = new Point(fromX, fromY);

				// 目标的x坐标在源对象的右边
				if (toX >= fromShape.getRightCenter().getX()) {
					tmpx = toX + Offset;
				} else {
					tmpx = fromX + Offset;
				}
				// 目标在源的上方
				if (toShape.getTopCenter().getY() <= fromY) {
					tmpy = toY - Offset;
				} else {
					tmpy = fromY - Offset;
				}
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * top bottom 检查完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopBottom(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 源对象在目标的右方
		if (fromX < toX) {
			// 右上方
			if (toY + minLen <= fromY) {
				float tmpy = (fromY + toY) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			} else {
				// 目标在源对象外侧
				if (fromShape.getRightCenter().getX() + minLen < toShape
						.getLeftCenter().getX()) {
					float tmpx = (toShape.getLeftCenter().getX() + fromShape
							.getRightCenter().getX()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				} else {
					// 目标对象上边缘在源对象的下方
					if (fromY <= toShape.getTopCenter().getY()) {
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(fromX, fromY - Offset);
						Point p3 = new Point(toShape.getRightCenter().getX()
								+ Offset, fromY - Offset);
						Point p4 = new Point(toShape.getRightCenter().getX()
								+ Offset, toY + Offset);
						Point p5 = new Point(toX, toY + Offset);
						Point p6 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
						list.add(p6);
					}
					// 目标对象上边缘在源对象的上方
					else {
						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(fromX, toShape.getTopCenter()
								.getY() - Offset);
						Point p3 = new Point(toShape.getRightCenter().getX()
								+ Offset, toShape.getTopCenter().getY()
								- Offset);
						Point p4 = new Point(toShape.getRightCenter().getX()
								+ Offset, toY + Offset);
						Point p5 = new Point(toX, toY + Offset);
						Point p6 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
						list.add(p6);

					}
				}
			}
		}
		// 横坐标一致
		else if (fromX == toX) {
			// 目标在源对象上方
			if (toY < fromY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			}
			// 中部
			else if (toY >= fromY && toShape.getTopCenter().getY() <= fromY) {
				float fx = fromShape.getLeftCenter().getX();
				float tx = toShape.getLeftCenter().getX();
				float tmpx = Math.min(fx, tx);

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toShape.getTopCenter().getY()
						- Offset);
				Point p3 = new Point(tmpx - Offset, toShape.getTopCenter()
						.getY() - Offset);
				Point p4 = new Point(tmpx - Offset, toShape.getTopCenter()
						.getY() + Offset);
				Point p5 = new Point(toX, toShape.getTopCenter().getY()
						+ Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
			// 下方
			else {
				float fx = fromShape.getLeftCenter().getX();
				float tx = toShape.getLeftCenter().getX();
				float tmpx = Math.min(fx, tx);

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY - Offset);
				Point p3 = new Point(tmpx - Offset, fromY - Offset);
				Point p4 = new Point(tmpx - Offset, toY + Offset);
				Point p5 = new Point(toX, toY + Offset);
				Point p6 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 左边
		else {
			// 上方
			if (toY + minLen < fromY) {
				float tmpy = (fromY + toY) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 下方
			else {
				// 目标在源对象左边
				if (toShape.getRightCenter().getX() + minLen < fromShape
						.getLeftCenter().getX()) {
					float tmpx = (fromShape.getLeftCenter().getX() + toShape
							.getRightCenter().getX()) / 2;

					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY + Offset);
					Point p5 = new Point(toX, toY + Offset);
					Point p6 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
					list.add(p6);
				}
				// 目标在源对象的中偏左
				else {
					// 目标在源对象的下方
					if (fromY <= toShape.getTopCenter().getY()) {
						float fx = fromShape.getLeftCenter().getX();
						float tx = toShape.getLeftCenter().getX();
						float tmpx = Math.min(fx, tx);

						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(fromX, fromY - Offset);
						Point p3 = new Point(tmpx - Offset, fromY - Offset);
						Point p4 = new Point(tmpx - Offset, toY + Offset);
						Point p5 = new Point(toX, toY + Offset);
						Point p6 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
						list.add(p6);
					}
					// 目标在源的上方
					else {
						float fx = fromShape.getLeftCenter().getX();
						float tx = toShape.getLeftCenter().getX();
						float tmpx = Math.min(fx, tx);

						Point p1 = new Point(fromX, fromY);
						Point p2 = new Point(fromX, toShape.getTopCenter()
								.getY() - Offset);
						Point p3 = new Point(tmpx - Offset, toShape
								.getTopCenter().getY() - Offset);
						Point p4 = new Point(tmpx - Offset, toY + Offset);
						Point p5 = new Point(toX, toY + Offset);
						Point p6 = new Point(toX, toY);

						list.add(p1);
						list.add(p2);
						list.add(p3);
						list.add(p4);
						list.add(p5);
						list.add(p6);
					}
				}
			}
		}
		return list;
	}

	/**
	 * top left 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateTopLeft(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();

		float toLeftX = toShape.getLeftCenter().getX();
		// 右边
		if (toLeftX + minLen > fromX) {
			// 上方
			if (fromY > toY + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
			// 下方
			else {
				// 右边
				if (fromShape.getRightCenter().getX() + minLen < toX) {
					float tmpx = (toX + fromShape.getRightCenter().getX()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, fromY - Offset);
					Point p3 = new Point(tmpx, fromY - Offset);
					Point p4 = new Point(tmpx, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				} else {
					float tmpy = 0;
					if (toShape.getTopCenter().getY() <= fromY) {
						tmpy = toShape.getTopCenter().getY() - Offset;
					} else {
						tmpy = fromY - Offset;
					}
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(fromShape.getLeftCenter().getX()
							- Offset, tmpy);
					Point p4 = new Point(fromShape.getLeftCenter().getX()
							- Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		}
		// 左边
		else {
			// 左上方
			if (fromY >= toShape.getBottomCenter().getY() + minLen) {
				float tmpy = (fromY + toShape.getBottomCenter().getY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY - tmpy);
				Point p3 = new Point(toX - Offset, fromY - tmpy);
				Point p4 = new Point(toX - Offset, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 左下方
			else {
				float tmpy = 0;
				// 目标在源对象上面
				if (toShape.getTopCenter().getY() < fromY) {
					tmpy = toY - Offset;
				} else {
					tmpy = fromY - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX - Offset, tmpy);
				Point p4 = new Point(toX - Offset, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	// right

	/**
	 * right top 检查完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightTop(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();

		// 目标在源对象右边
		if (fromX + minLen < toShape.getLeftCenter().getX()) {
			// 上方
			if (fromY > toY + minLen) {
				float tmpx = (toShape.getLeftCenter().getX() + fromX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY - Offset);
				Point p4 = new Point(toX, toY - Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 下方
			else {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
		} else { // 目标在源的左边
			if (fromShape.getBottomCenter().getY() + minLen >= toY) {// 上方
				float tmpy = 0;
				float tmpx = 0;
				// 上方
				if (fromShape.getTopCenter().getY() + minLen >= toY) {
					tmpy = toY - Offset;
				} else {
					tmpy = fromShape.getTopCenter().getY() - Offset;
				}
				tmpx = fromX + Offset;

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
			// 下方
			else {
				if (toX < fromX) {
					float tmpy = (fromShape.getBottomCenter().getY() + toY) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX + Offset, fromY);
					Point p3 = new Point(fromX + Offset, tmpy);
					Point p4 = new Point(toX, tmpy);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				} else {
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(toX, fromY);
					Point p3 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
				}
			}
		}
		return list;
	}

	/**
	 * right right 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightRight(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标在源对象的右边
		if (toShape.getLeftCenter().getX() >= minLen + fromX) {
			// 目标对象的底部坐标大于 源对象的Y坐标
			if (fromY > toShape.getBottomCenter().getY() + minLen
					|| fromY + minLen < toShape.getTopCenter().getY()) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX + Offset, fromY);
				Point p3 = new Point(toX + Offset, toY);
				Point p4 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			} else {
				float tmpx = (toShape.getLeftCenter().getX() + fromX) / 2;
				float tmpy = 0;
				if (fromY > toY && fromY < toShape.getBottomCenter().getY()) {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = toShape.getTopCenter().getY() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX + Offset, tmpy);
				Point p5 = new Point(toX + Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 中部判断
		else if (toShape.getLeftCenter().getX() < fromX + minLen
				&& toX > fromShape.getRightCenter().getX() + minLen) {
			float tmpx = 0;
			if (toX > fromX) {
				tmpx = toX + Offset;
			} else {
				tmpx = fromX + Offset;
			}

			if (fromY == toY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		}
		// 目标对象在左边
		else {
			// 上方 下方
			if (toY + minLen <= fromShape.getTopCenter().getY()
					|| fromShape.getBottomCenter().getY() + minLen <= toY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				float tmpx = (fromShape.getLeftCenter().getX() + toX) / 2;
				float tmpy = 0;
				if (fromY < toY
						&& toY < fromShape.getBottomCenter().getY() + minLen) {
					tmpy = fromShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = fromShape.getTopCenter().getY() - Offset;
				}

				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, tmpy);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(tmpx, toY);
				Point p6 = new Point(fromX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	/**
	 * right bottom 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightBottom(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标对象在源对象的右边
		if (toShape.getBottomCenter().getX() > fromX + minLen) {
			// 上方
			if (fromY > toY + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
			// 下方
			else {
				float tmpx = (fromX + toShape.getLeftCenter().getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY + Offset);
				Point p4 = new Point(toX, toY + Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		} else {
			// 上方
			if (toY + minLen < fromShape.getTopCenter().getY()) {
				float tmpy = (fromShape.getTopCenter().getY() + toY) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getRightCenter().getX() > fromX) {
					tmpx = toX + Offset;
				} else {
					tmpx = fromX + Offset;
				}
				if (toY < fromShape.getBottomCenter().getY()) {
					tmpy = fromShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * right left 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateRightLeft(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 右边
		if (toShape.getLeftCenter().getX() > fromX + minLen) {
			// 直线
			if (toY == fromY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			}
			// 纵坐标不相同
			else {
				float tmpx = (fromX + toX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		} else {
			// 上方,下方
			if (toShape.getBottomCenter().getY() + minLen < fromShape
					.getTopCenter().getY()
					|| fromShape.getBottomCenter().getY() + minLen < toShape
							.getTopCenter().getY()) {
				float tmpy = 0;
				if (toShape.getBottomCenter().getY() + minLen < fromShape
						.getTopCenter().getY()) {
					tmpy = (toShape.getBottomCenter().getY() + fromShape
							.getTopCenter().getY()) / 2;
				} else {
					tmpy = (toShape.getTopCenter().getY() + fromShape
							.getBottomCenter().getY()) / 2;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX + Offset, fromY);
				Point p3 = new Point(fromX + Offset, tmpy);
				Point p4 = new Point(toX - Offset, tmpy);
				Point p5 = new Point(toX - Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
			// 中部
			else {
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getRightCenter().getX() > fromX) {
					tmpx = toShape.getRightCenter().getX() + Offset;
				} else {
					tmpx = fromX + Offset;
				}
				if (toY < fromY
						&& toShape.getTopCenter().getY() < fromShape
								.getTopCenter().getX()) {
					tmpy = toShape.getTopCenter().getY() - Offset;
				} else {
					tmpy = fromShape.getTopCenter().getY() - Offset;
				}
				if (toY >= fromY
						&& toShape.getBottomCenter().getY() > fromShape
								.getBottomCenter().getY()) {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = fromShape.getBottomCenter().getY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX - Offset, tmpy);
				Point p5 = new Point(toX - Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	// left

	/**
	 * left top 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftTop(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();

		if (fromX + minLen < toShape.getLeftCenter().getX()) {
			if (toY >= fromShape.getBottomCenter().getY() + minLen) {
				float tmpy = (fromShape.getBottomCenter().getY() + toY) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				float tmpx = 0;
				float tmpy = 0;
				if (toShape.getLeftCenter().getX() >= fromX) {
					tmpx = toShape.getLeftCenter().getX() - Offset;
				} else {
					tmpx = fromShape.getLeftCenter().getX() - Offset;
				}
				if (fromShape.getTopCenter().getY() > toY) {
					tmpy = toY - Offset;
				} else {
					tmpy = fromShape.getTopCenter().getY();
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		} else {
			if (toY >= fromY + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {
				float tmpx = (toShape.getRightCenter().getX() + fromX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY - Offset);
				Point p4 = new Point(toX, toY - Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * left right 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftRight(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标在源对象的左边
		if (toX + minLen <= fromX) {
			// 纵坐标相等
			if (fromY == toY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			} else {
				float tmpx = (toX + fromX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		}
		// 目标在源对象的右边
		else {
			// 上方下方
			if (toShape.getBottomCenter().getY() + minLen <= fromShape
					.getTopCenter().getY()
					|| toShape.getTopCenter().getY() >= fromShape
							.getBottomCenter().getY() + minLen) {
				float tmpy = 0;
				if (toShape.getBottomCenter().getY() + minLen <= fromShape
						.getTopCenter().getY()) {
					tmpy = (toShape.getBottomCenter().getY() + fromShape
							.getTopCenter().getY()) / 2;
				} else {
					tmpy = (toShape.getTopCenter().getY() + fromShape
							.getBottomCenter().getY()) / 2;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, tmpy);
				Point p4 = new Point(toX + Offset, tmpy);
				Point p5 = new Point(toX + Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			} else {
				float tmpx = 0;
				float tmpy = 0;
				// 目标在源的左边就以目标的横坐标为准。
				if (toShape.getLeftCenter().getX() < fromX) {
					tmpx = toShape.getLeftCenter().getX() - Offset;
				} else {
					tmpx = fromX - Offset;
				}
				// 线条在上方
				if (fromY > toY) {
					if (toShape.getTopCenter().getY() < fromShape
							.getTopCenter().getY()) {
						tmpy = toShape.getTopCenter().getY() - Offset;
					} else {
						tmpy = fromShape.getTopCenter().getY() - Offset;
					}
				}
				// 线条在下方
				else {
					if (toShape.getBottomCenter().getY() > fromShape
							.getBottomCenter().getY()) {
						tmpy = toShape.getBottomCenter().getY() + Offset;
					} else {
						tmpy = fromShape.getBottomCenter().getY() + Offset;
					}
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX + Offset, tmpy);
				Point p5 = new Point(toX + Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	/**
	 * Left Bottom 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftBottom(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标对像在源对象的左边
		if (toX + minLen < fromX) {
			// 左上部
			if (toY + minLen <= fromY) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, fromY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
			// 左下部
			else {
				float tmpx = (toShape.getRightCenter().getX() + fromX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, toShape.getBottomCenter().getY()
						+ Offset);
				Point p4 = new Point(toX, toShape.getBottomCenter().getY()
						+ Offset);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}

		}
		// 目标对象在源对象右边
		else {
			// 目标对象在源对象的上方
			if (toShape.getBottomCenter().getY() + minLen < fromShape
					.getTopCenter().getY()) {
				float tmpy = (toShape.getBottomCenter().getY() + fromShape
						.getTopCenter().getY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				float tmpx = 0;
				float tmpy = 0;
				// 目标的左边界在源坐标的左边
				if (toShape.getLeftCenter().getX() < fromX) {
					tmpx = toShape.getLeftCenter().getX() - Offset;
				} else {
					tmpx = fromX - Offset;
				}

				if (toShape.getBottomCenter().getY() < fromShape
						.getBottomCenter().getY()) {
					tmpy = fromShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX, tmpy);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * left left 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateLeftLeft(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标在源对象的左边
		if (toShape.getLeftCenter().getX() + minLen <= fromX) {
			System.out.println("caculateLeftLeft目标在源对象的左边");
			// 上方下方
			if (toShape.getBottomCenter().getY() + minLen < fromY
					|| toShape.getTopCenter().getY() > fromY + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX - Offset, fromY);
				Point p3 = new Point(toX - Offset, toY);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				float tmpx = (toShape.getRightCenter().getX() + fromY) / 2;
				float tmpy = 0;

				if (toY > fromY) {
					tmpy = toShape.getTopCenter().getY() - Offset;
				} else {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(tmpx, fromY);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(toX - Offset, tmpy);
				Point p5 = new Point(toX - Offset, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 中部
		else if (toShape.getRightCenter().getX() + minLen > fromX
				&& toShape.getLeftCenter().getX() < fromShape.getRightCenter()
						.getX() + minLen) {
			System.out.println("caculateLeftLeft中部");
			float tmpx = 0;
			if (toX < fromX) {
				tmpx = toX - Offset;
			} else {
				tmpx = fromX - Offset;
			}
			Point p1 = new Point(fromX, fromY);
			Point p2 = new Point(tmpx, fromY);
			Point p3 = new Point(tmpx, toY);
			Point p4 = new Point(toX, toY);
			list.add(p1);
			list.add(p2);
			list.add(p3);
			list.add(p4);
		}
		// 右边
		else {
			System.out.println("caculateLeftLeft右边");
			// 上方下方
			if (toY + minLen < fromShape.getTopCenter().getY()
					|| toY > fromShape.getBottomCenter().getY() + minLen) {

				System.out.println("caculateLeftLeft上方下方");
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, toY);
				Point p4 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 纵向中部
			else {
				System.out.println("caculateLeftLeft纵向中部");
				float tmpx = (fromShape.getRightCenter().getX() + toX) / 2;
				float tmpy = 0;
				// 目标在源的中上
				if (fromY > toY) {
					tmpy = fromShape.getTopCenter().getY() - Offset;
				} else {
					tmpy = fromShape.getBottomCenter().getY() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX - Offset, fromY);
				Point p3 = new Point(fromX - Offset, tmpy);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(tmpx, toY);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		return list;
	}

	// bottom

	/**
	 * bottom top 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomTop(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 上方
		if (toY < fromY + minLen) {
			// 左边和右边
			if (toShape.getRightCenter().getX() + minLen < fromShape
					.getLeftCenter().getX()
					|| toShape.getLeftCenter().getX() > fromShape
							.getRightCenter().getX() + minLen) {
				float tmpx = 0;
				if (toShape.getRightCenter().getX() + minLen < fromShape
						.getLeftCenter().getX()) {
					tmpx = (toShape.getRightCenter().getX() + fromShape
							.getLeftCenter().getX()) / 2;
				} else {
					tmpx = (toShape.getLeftCenter().getX() + fromShape
							.getRightCenter().getX()) / 2;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, toY - Offset);
				Point p5 = new Point(toX, toY - Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
			// 中部
			else {
				float tmpx = 0;
				float tmpy = 0;
				// 目标在源的中偏左
				if (toX > fromX) {
					if (toShape.getRightCenter().getX() > fromShape
							.getRightCenter().getX()) {
						tmpx = toShape.getRightCenter().getX() + Offset;
					} else {
						tmpx = fromShape.getRightCenter().getX() + Offset;
					}
				} else {
					if (toShape.getLeftCenter().getX() < fromShape
							.getLeftCenter().getX()) {
						tmpx = toShape.getLeftCenter().getX() - Offset;
					} else {
						tmpx = fromShape.getLeftCenter().getX() - Offset;
					}
				}
				if (toShape.getBottomCenter().getY() > fromY) {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = fromY + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY - Offset);
				Point p5 = new Point(toX, toY - Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);

			}
		}
		// 目标在源的下方
		else {
			if (toX == fromX) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(toX, toY);
				list.add(p1);
				list.add(p2);
			} else {
				float tmpy = (fromShape.getBottomCenter().getY() + toShape
						.getTopCenter().getY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
		}

		return list;
	}

	/**
	 * bottom right 完成
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomRight(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 目标在源对象的下方
		if (toY > fromY + minLen) {
			if (toX <= fromX) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			} else {
				// 下方
				if (toShape.getTopCenter().getY() > fromY + minLen) {
					float tmpy = (fromY + toShape.getTopCenter().getY()) / 2;
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, tmpy);
					Point p3 = new Point(toX + Offset, tmpy);
					Point p4 = new Point(toX + Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);

				} else {
					Point p1 = new Point(fromX, fromY);
					Point p2 = new Point(fromX, toY + Offset);
					Point p3 = new Point(toX + Offset, toY + Offset);
					Point p4 = new Point(toX + Offset, toY);
					Point p5 = new Point(toX, toY);

					list.add(p1);
					list.add(p2);
					list.add(p3);
					list.add(p4);
					list.add(p5);
				}
			}
		} else {
			// 在左边
			if (toX < fromShape.getLeftCenter().getX()) {
				float tmpx = (toX + fromShape.getLeftCenter().getX()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				float tmpx = 0;
				float tmpy = 0;
				if (toX > fromShape.getRightCenter().getX()) {
					tmpx = toX + Offset;
				} else {
					tmpx = fromShape.getRightCenter().getX();
				}
				if (toShape.getBottomCenter().getY() > fromY) {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = fromY + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	/**
	 * bottom bottom 完毕
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomBottom(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 上方
		if (toY + minLen < fromShape.getTopCenter().getY()) {
			// 左方右方
			if (toX + minLen < fromShape.getLeftCenter().getX()
					|| toX > fromShape.getRightCenter().getX() + minLen) {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(toX, fromY + Offset);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				float tmpy = (toY + fromShape.getTopCenter().getY()) / 2;
				float tmpx = 0;
				if (toX < fromX) {
					tmpx = fromShape.getLeftCenter().getX() - Offset;
				} else {
					tmpx = fromShape.getRightCenter().getX() + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, tmpy);
				Point p5 = new Point(toX, tmpy);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}
		// 下方
		else {
			// 左右边
			if (toShape.getLeftCenter().getX() > fromX + minLen
					|| toShape.getRightCenter().getX() + minLen < fromX) {
				float tmpy = 0;
				if (toY > fromY) {
					tmpy = toY + Offset;
				} else {
					tmpy = fromY + Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX, tmpy);
				Point p4 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
			}
			// 中部
			else {
				float tmpx = 0;
				float tmpy = (toShape.getTopCenter().getY() + fromY) / 2;
				if (toX < fromX) {
					tmpx = toShape.getRightCenter().getX() + Offset;
				} else {
					tmpx = toShape.getLeftCenter().getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY + Offset);
				Point p5 = new Point(toX, toY + Offset);
				Point p6 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
				list.add(p6);
			}
		}

		return list;
	}

	/**
	 * bottom left
	 * 
	 * @param fromShape
	 * @param toShape
	 * @return
	 */
	public static ArrayList<Point> caculateBottomLeft(Shape fromShape,
			Shape toShape) {
		ArrayList<Point> list = new ArrayList<Point>();
		float fromX = fromShape.getPoint().getX();
		float fromY = fromShape.getPoint().getY();
		float toX = toShape.getPoint().getX();
		float toY = toShape.getPoint().getY();
		// 下方
		if (fromY + minLen < toShape.getTopCenter().getY()) {
			if (fromX > toX) {
				float tmpy = (fromY + toShape.getTopCenter().getY()) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(toX - Offset, tmpy);
				Point p4 = new Point(toX - Offset, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, toY);
				Point p3 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
			}
		} else {
			if (toX < fromShape.getRightCenter().getX() + minLen) {
				float tmpy = 0;
				float tmpx = 0;
				if (toShape.getBottomCenter().getY() > fromY) {
					tmpy = toShape.getBottomCenter().getY() + Offset;
				} else {
					tmpy = fromY + Offset;
				}
				if (toX < fromShape.getRightCenter().getX()) {
					tmpx = toX - Offset;
				} else {
					tmpx = fromShape.getRightCenter().getX() - Offset;
				}
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, tmpy);
				Point p3 = new Point(tmpx, tmpy);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			} else {
				float tmpx = (fromShape.getRightCenter().getX() + toX) / 2;
				Point p1 = new Point(fromX, fromY);
				Point p2 = new Point(fromX, fromY + Offset);
				Point p3 = new Point(tmpx, fromY + Offset);
				Point p4 = new Point(tmpx, toY);
				Point p5 = new Point(toX, toY);

				list.add(p1);
				list.add(p2);
				list.add(p3);
				list.add(p4);
				list.add(p5);
			}
		}
		return list;
	}

	private static String getPointXml(ArrayList<Point> list) {
		StringBuffer sb = new StringBuffer();
		for (Point p : list) {
			sb.append("\n<omgdi:waypoint x=\"" + p.getX() + "\" y=\""
					+ p.getY() + "\"></omgdi:waypoint>\n");
		}
		return sb.toString();
	}

}
