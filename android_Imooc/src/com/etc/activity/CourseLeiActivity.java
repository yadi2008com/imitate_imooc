package com.etc.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.R.color;
import android.app.Activity;
 
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
 
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.b.R;

public class CourseLeiActivity extends  Activity   {
	private TabHost myTabHost;
	private int[] layRes = new int[] { R.id.tab_focus, R.id.tab_studied };
 	private ListView lv_courselist=null;
 	private ListView lv_courselist2=null;
	private SimpleAdapter simpleAdapter=null;
    private final String CITY_PATH_JSON="http://119.29.85.27/Imooc/MyCourseServlet?type=json&userid=4";
    private final String CITY_PATH_JSON2="http://119.29.85.27/Imooc/MyCourseYxServlet?type=json&userid=4";
	  
    //ListView listView;
    List<Map<String,Object>> data;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_course);
		 
         this.myTabHost=(TabHost) super.findViewById(R.id.tabhost);
         this.myTabHost.setup();
         
           
		for (int x = 0; x < this.layRes.length; x++) {
			TabSpec myTab = this.myTabHost.newTabSpec("tab" + x);
			
			if (x==0) {
				 
				myTab.setIndicator("关注");
				new MyAsyncTask().execute(CITY_PATH_JSON);
				 
			}
			if(x==1){
				myTab.setIndicator("历史");
				 
				 new MyAsyncTask2().execute(CITY_PATH_JSON2);
			}
			
			myTab.setContent(this.layRes[x]);
			this.myTabHost.addTab(myTab);
		}
		 updateTab(myTabHost);
		 
		
        lv_courselist=(ListView)findViewById(R.id.lv_courselist);
        lv_courselist2=(ListView)findViewById(R.id.lv_courselist2);
        
           
        this.myTabHost.setCurrentTab(0);
         
		  
	}
	 
	
	 private void updateTab(final TabHost tabHost) { 
	        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) { 
	            View view = tabHost.getTabWidget().getChildAt(i); 
	            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); 
	            tv.setTextSize(16); 
	            tv.setTypeface(Typeface.SERIF, 2); // 设置字体和风格  
	            if (tabHost.getCurrentTab() == i) {//选中  
	                view.setBackgroundDrawable(getResources().getDrawable(color.holo_red_dark));//选中后的背景  
	                tv.setTextColor(this.getResources().getColorStateList( 
	                        android.R.color.black)); 
	            } else {//不选中  
	                view.setBackgroundDrawable(getResources().getDrawable(color.holo_red_dark));//非选择的背景  
	                tv.setTextColor(this.getResources().getColorStateList( 
	                        android.R.color.black)); 
	            } 
	        } 
	    } 
	//从PC端获取关注的列表信息
	public class MyAsyncTask extends AsyncTask<String,Void,List<Map<String, Object>>>{

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			String coursesString="";
			HttpClient httpClient=new DefaultHttpClient();
			try {
				HttpPost httpPost=new HttpPost(params[0]);
				HttpResponse httpResponse=httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
					HttpEntity httpEntity=httpResponse.getEntity();
					if (httpEntity!=null) {
						coursesString=EntityUtils.toString(httpEntity,"utf-8");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				httpClient.getConnectionManager().shutdown();
			}
			try {
				 List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
				 Map<String,Object > map;

		      JSONObject jsonObject = new JSONObject(coursesString);
					JSONArray jsonArray = jsonObject.getJSONArray("courseBeanList");
					JSONObject jsonObject2 = new JSONObject();
					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject2 = jsonArray.getJSONObject(i);
						 map = new HashMap<String, Object>();
						 String cour_image=jsonObject2.getString("cour_image");
						 map.put("images", returnBitMap("http://119.29.85.27/Imooc/CourseImages/"+cour_image));
						 map.put("cour_title", jsonObject2.getString("cour_title"));
						 map.put("cour_duration", jsonObject2.getString("cour_duration"));
						 map.put("level_name", jsonObject2.getString("level_name"));
						 data.add(map);
						
					}
					
					 return data;
			      
			}
			 catch (Exception e) {
				e.printStackTrace();
			}
			
			return data;
		}
		 
		
		protected void onPostExecute(List<Map<String, Object>> result){
			
			// 处理数据，显示到list
			 
			simpleAdapter = new SimpleAdapter(CourseLeiActivity.this,
					result, R.layout.activity_my_courselist, new String[] { 
					"images","cour_title","cour_duration","level_name"  }, new int[] { R.id.iv_courseimage,R.id.tv_mycour_name ,R.id.tv_cour_duration,R.id.tv_level_name });
		    simpleAdapter.setViewBinder(new ViewBinder(){
		           public boolean setViewValue(View view,Object data,String textRepresentation){
		                 
						if(view instanceof ImageView && data instanceof Bitmap){
		                 ImageView iv=(ImageView)view;
		                      iv.setImageBitmap((Bitmap) data);
		                                return true;
		                           }
		                              else 
		                              {
		                            	  return false;
		                              }
		                           }
		                      }); 
			 
			lv_courselist.setAdapter(simpleAdapter );
		}
		  
	  }
	
	
	//从PC端获取历史的列表信息
		public class MyAsyncTask2 extends AsyncTask<String,Void,List<Map<String, Object>>>{

			@Override
			protected List<Map<String, Object>> doInBackground(String... params) {
				String coursesString="";
				HttpClient httpClient=new DefaultHttpClient();
				try {
					HttpPost httpPost=new HttpPost(params[0]);
					HttpResponse httpResponse=httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
						HttpEntity httpEntity=httpResponse.getEntity();
						if (httpEntity!=null) {
							coursesString=EntityUtils.toString(httpEntity,"utf-8");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					httpClient.getConnectionManager().shutdown();
				}
				try {
					 List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
					 Map<String,Object > map;

			      JSONObject jsonObject = new JSONObject(coursesString);
						JSONArray jsonArray = jsonObject.getJSONArray("courseBeanList");
						JSONObject jsonObject2 = new JSONObject();
						for (int i = 0; i < jsonArray.length(); i++) {
							jsonObject2 = jsonArray.getJSONObject(i);
							 map = new HashMap<String, Object>();
							 String cour_image=jsonObject2.getString("cour_image");
							 map.put("images", returnBitMap("http://119.29.85.27/Imooc/CourseImages/"+cour_image));
							 map.put("cour_title", jsonObject2.getString("cour_title"));
							 map.put("cour_duration", jsonObject2.getString("cour_duration"));
							 map.put("level_name", jsonObject2.getString("level_name"));
							 data.add(map);
							
						}
						
						 return data;
				      
				}
				 catch (Exception e) {
					e.printStackTrace();
				}
				
				return data;
			}
			 
			
			protected void onPostExecute(List<Map<String, Object>> result){
				
				// 处理数据，显示到list
				simpleAdapter = new SimpleAdapter(CourseLeiActivity.this,
						result, R.layout.activity_my_courselist, new String[] { 
						"images","cour_title","cour_duration","level_name"  }, new int[] { R.id.iv_courseimage,R.id.tv_mycour_name ,R.id.tv_cour_duration,R.id.tv_level_name });
			    simpleAdapter.setViewBinder(new ViewBinder(){
			           public boolean setViewValue(View view,Object data,String textRepresentation){
			                 
							if(view instanceof ImageView && data instanceof Bitmap){
			                 ImageView iv=(ImageView)view;
			                      iv.setImageBitmap((Bitmap) data);
			                                return true;
			                           }
			                              else 
			                              {
			                            	  return false;
			                              }
			                           }
			                      }); 
				 
				lv_courselist2.setAdapter(simpleAdapter );
			}
			  
		  }
		
	 
	public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	 
} 
	


 
 
