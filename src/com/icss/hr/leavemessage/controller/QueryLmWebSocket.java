package com.icss.hr.leavemessage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.leavemessage.pojo.Lmb;
import com.icss.hr.leavemessage.service.LmbService;
/**
 * 服务器端WebSocket类
 * @author Administrator
 *
 */
@ServerEndpoint("/QueryLmWebSocket")
public class QueryLmWebSocket {
	public static ArrayList<Session> sessionList = new ArrayList<>();
	public static LmbService service = new LmbService();
	public static EmpService empservice = new EmpService();	
	/**
	 * 当客户端发送数据时事件回调
	 * @param message
	 * @param session
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws JSONException 
	 * @throws SQLException 
	 * @throws EncodeException 
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException, JSONException, EncodeException, SQLException {
	

		JSONArray jsonArr = new JSONArray(message);
		String lmbEmpId = jsonArr.getJSONObject(0).get("lmbEmpId").toString();
		String lmbText = jsonArr.getJSONObject(0).get("lmbText").toString();
		
		
		
		Lmb lmb = null;
		System.out.println(empservice.queryEmpByLoginName(lmbEmpId));
		if(empservice.queryEmpByLoginName(lmbEmpId) != null){
			lmb = new Lmb(empservice.queryEmpByLoginName(lmbEmpId),lmbText,new Date());
			
			service.addLm(lmb);
		}
//		System.out.println(lmb);
		StringBuffer json = new StringBuffer();
		
		json.append("[{");
		json.append("\"lmbEmpId\":\"" + lmbEmpId + "\"");
		json.append(",\"lmbEmpName\":\"" + lmb.getLmbEmp().getEmpName()+ "\"");
		json.append(",\"lmbText\":\"" + lmbText+ "\"");
		json.append(",\"lmbDate\":\"" + lmb.getLmbDate()+ "\"");
		json.append("}]");
		
		// 向客户端发送消息
		
		for(int i = 0;i < sessionList.size();i ++){
			sessionList.get(i).getBasicRemote().sendText(json.toString());
		}
		
	}

	/**
	 * 客户端连接成功事件回调
	 * @throws JSONException 
	 * @throws SQLException 
	 * @throws EncodeException 
	 * @throws IOException 
	 */
	@OnOpen
	public void onOpen(Session session) throws JSONException, SQLException, IOException, EncodeException{
		sessionList.add(session);
		
		ArrayList<Lmb> list = service.QuetyMainLm();
		
		StringBuffer json = new StringBuffer("[");
		
		for(int i =0;i < list.size();i ++){
			if(i == 0)
				json.append("{");
			else
				json.append(",{");
			json.append("\"lmbId\":\""+ list.get(i).getLmbId() + "\"");
			json.append(",\"lmbEmpId\":\"" + list.get(i).getLmbEmp().getEmpId()+ "\"");
			json.append(",\"lmbEmpName\":\"" + list.get(i).getLmbEmp().getEmpName() + "\"");
			json.append(",\"lmbText\":\"" + list.get(i).getLmbText()+"\"");
			if(list.get(i).getCommentEmp() == null){
				json.append(",\"commentEmpId\":\"null\"");
				json.append(",\"commentEmpName\":\"null\"");
			}else{
				json.append(",\"commentEmpId\":\""+ list.get(i).getCommentEmp().getEmpId()+ "\"");
				json.append(",\"commentEmpName\":\"" + list.get(i).getCommentEmp().getEmpName() +"\"");
			}
			json.append(",\"lmbDate\":\"" + list.get(i).getLmbDate()+"\"");
			json.append("}");
		}
		json.append("]");

		session.getBasicRemote().sendText(json.toString());
		System.out.println("连接被打开");
	}
	
	/**
	 * 连接被关闭事件回调
	 * @throws JSONException 
	 */
	@OnClose
	public void onClose(Session session) throws JSONException {

		for(int i = 0;i < sessionList.size();i ++){
			if(sessionList.get(i) == session){
				sessionList.remove(i);
			}
		}
		
		System.out.println("连接被关闭");
	}
}