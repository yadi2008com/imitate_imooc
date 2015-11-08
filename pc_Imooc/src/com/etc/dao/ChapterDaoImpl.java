package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.etc.bean.ChapterBean;
import com.etc.bean.PageBean;
import com.etc.bean.SectionBean;
import com.etc.util.DBUtil;
 

public class ChapterDaoImpl implements ChapterDao{
	DBUtil dbUtil=new DBUtil();
	Connection connection =null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
 

	@Override
	public Map<Integer,List> fetchChapterList(int planid) throws Exception {
		Map<Integer,List> map = new HashMap<Integer,List>();
		List<ChapterBean> chapterBeanList=null;
		List<SectionBean>  sectionBeanList=null;
		connection=dbUtil.getConnection();
		String sql2 = "select * from chapter where plan_id =?";
		preparedStatement= connection.prepareStatement(sql2);
		preparedStatement.setInt(1,  planid);
		 
		resultSet=preparedStatement.executeQuery();
		chapterBeanList=new ArrayList<ChapterBean>();
		while(resultSet.next()){
			ChapterBean chapterBean2=new ChapterBean();
			chapterBean2.setChapter_id(resultSet.getInt("chapter_id"));
			chapterBean2.setChap_name(resultSet.getString("chap_name"));
			chapterBean2.setChap_content(resultSet.getString("chap_content"));
			chapterBeanList.add(chapterBean2);
		}
		map.put(0,chapterBeanList);
		StringBuffer sb = new StringBuffer();
		sb.append("select * from section where ");
		for(int i = 0; i < chapterBeanList.size(); i++){
			if(i == 0){
				sb.append("chap_id = "+chapterBeanList.get(i).getChapter_id());
			}else{
				sb.append(" or chap_id = "+chapterBeanList.get(i).getChapter_id());
			}
			
		}
		
		preparedStatement = connection.prepareStatement(sb.toString());
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			if(sectionBeanList == null){
				sectionBeanList = new ArrayList<SectionBean>();
			}
			SectionBean sectionBean2=new SectionBean();
			sectionBean2.setSection_id(resultSet.getInt("section_id"));
			sectionBean2.setSect_name(resultSet.getString("sect_name"));
			sectionBean2.setChap_id((resultSet.getInt("chap_id")));;
			sectionBean2.setSection_content(resultSet.getString("section_content"));
			sectionBean2.setSection_img(resultSet.getString("section_img"));
			sectionBeanList.add(sectionBean2);
		}
		map.put(1, sectionBeanList);
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return map;
	}
	@Override
	public int chapterInsert(ChapterBean chapterBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into chapter (chap_name,chap_content,plan_id) values (?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, chapterBean.getChap_name());
		preparedStatement.setString(2, chapterBean.getChap_content());
		preparedStatement.setInt(3, chapterBean.getPlan_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public ChapterBean chapterEdit(int id) throws Exception {
		ChapterBean chapterBean = null;
		connection = dbUtil.getConnection();
		String sql = "select * from chapter where chapter_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			chapterBean = new ChapterBean();
			chapterBean.setChap_name(resultSet.getString("chap_name"));
			chapterBean.setChap_content(resultSet.getString("chap_content"));
			chapterBean.setChapter_id(resultSet.getInt("chapter_id"));
			chapterBean.setPlan_id(resultSet.getInt("plan_id"));
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return chapterBean;
	}

	@Override
	public List<ChapterBean> chapterShow() throws Exception {
		List<ChapterBean> chapterBeanList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from chapter";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		chapterBeanList = new ArrayList<ChapterBean>();
		while (resultSet.next()) {
			ChapterBean chapterBean = new ChapterBean();
			chapterBean.setChapter_id(resultSet.getInt("chapter_id"));
			chapterBean.setChap_name(resultSet.getString("chap_name"));
			chapterBean.setChap_content(resultSet.getString("chap_content"));
			chapterBean.setPlan_id(resultSet.getInt("plan_id"));
			chapterBeanList.add(chapterBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return chapterBeanList;
	}

	@Override
	public int chapterDelete(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from chapter where chapter_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int chapterUpdate(ChapterBean chapterBean) throws Exception {
		int result = 0;
		connection = dbUtil.getConnection();
		String sql = "update chapter set chap_name=?,chap_content=?,plan_id=? where chapter_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, chapterBean.getChap_name());
		preparedStatement.setString(2, chapterBean.getChap_content());
		preparedStatement.setInt(3, chapterBean.getPlan_id());
		preparedStatement.setInt(4, chapterBean.getChapter_id());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int chapterRows() throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from chapter";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<ChapterBean> chapterPageShow(int pageno) throws Exception {
		List<ChapterBean> chapterBeanList = null;
		connection = dbUtil.getConnection();
		String sql = " select chapter.*, plan.plan_name from chapter,plan where chapter.plan_id=plan.plan_id order by plan.plan_id   limit ?,? ";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		chapterBeanList = new ArrayList<ChapterBean>();
		while (resultSet.next()) {
			ChapterBean chapterBean = new ChapterBean();
			chapterBean.setChapter_id(resultSet.getInt("chapter_id"));
			chapterBean.setChap_name(resultSet.getString("chap_name"));
			chapterBean.setChap_content(resultSet.getString("chap_content"));
			chapterBean.setPlan_name(resultSet.getString("plan_name"));
			chapterBeanList.add(chapterBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return chapterBeanList;
	}
	//解决JSON keys must be strings
			public Map<String,List> fetchChapterListString(int planid) throws Exception {
				Map<String,List> map = new HashMap<String,List>();
				List<ChapterBean> chapterBeanList=null;
				List<SectionBean>  sectionBeanList=null;
				connection=dbUtil.getConnection();
				String sql2 = "select * from chapter where plan_id =?";
				preparedStatement= connection.prepareStatement(sql2);
				preparedStatement.setInt(1,  planid);
				 
				resultSet=preparedStatement.executeQuery();
				chapterBeanList=new ArrayList<ChapterBean>();
				while(resultSet.next()){
					ChapterBean chapterBean2=new ChapterBean();
					chapterBean2.setChapter_id(resultSet.getInt("chapter_id"));
					chapterBean2.setChap_name(resultSet.getString("chap_name"));
					chapterBean2.setChap_content(resultSet.getString("chap_content"));
					chapterBeanList.add(chapterBean2);
				}
				map.put("chapter",chapterBeanList);
				StringBuffer sb = new StringBuffer();
				sb.append("select * from section where ");
				for(int i = 0; i < chapterBeanList.size(); i++){
					if(i == 0){
						sb.append("chap_id = "+chapterBeanList.get(i).getChapter_id());
					}else{
						sb.append(" or chap_id = "+chapterBeanList.get(i).getChapter_id());
					}
					
				}
				
				preparedStatement = connection.prepareStatement(sb.toString());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					if(sectionBeanList == null){
						sectionBeanList = new ArrayList<SectionBean>();
					}
					SectionBean sectionBean2=new SectionBean();
					sectionBean2.setSection_id(resultSet.getInt("section_id"));
					sectionBean2.setSect_name(resultSet.getString("sect_name"));
					sectionBean2.setChap_id((resultSet.getInt("chap_id")));;
					sectionBean2.setSection_content(resultSet.getString("section_content"));
					sectionBean2.setSection_img(resultSet.getString("section_img"));
					sectionBeanList.add(sectionBean2);
				}
				map.put("section", sectionBeanList);
				dbUtil.closeDBSource(connection, preparedStatement, resultSet);
				return map;
			}


}
