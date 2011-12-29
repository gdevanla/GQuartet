package com.gquartet;

import java.util.Date;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.*;
import com.gquartet.data.*;


import java.io.IOException;
import javax.servlet.http.*;
import java.util.logging.Logger;
import java.util.*;


@SuppressWarnings("serial")
public class TestGQDataStore extends HttpServlet {

  static final Logger log = Logger.getLogger(TestGQDataStore.class.getName()); 

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

      
      log.warning(req.getParameter("action"));
      String action = req.getParameter("action");

      StringBuilder data = new StringBuilder();
    
      if ( "addnewtalk".equals(action) )
       {

        data.append(action).append(" performed \n " );


        data.append ( GQDataStore.AddNewTalk("presentation:0AYyutri7KO7bZDlycjRyY18wZGo5cWd6OHA"
                , new Date() 
                , (String)req.getParameter("talkname") ) );
 
      }

      if ( "getTalks".equals(action) )
      {

        Map<String, Talk>  list = GQDataStore.GetTalks();
        for ( Map.Entry<String,Talk> entry : list.entrySet() )
        {
          Talk t = entry.getValue();
          log.warning("Talk Name :"  + t.talkName );
          data.append(t.talkName).append(" ").append(t.resourceId).append(" ").append(t.dateTime).append("\n");
        }
      }

     if ( "getTalkByTalkName".equals(action) )
     {
          Talk t  = GQDataStore.GetTalkByTalkName((String)req.getParameter("talkname"));
          if ( t != null )
            data.append(t.talkName).append(" ").append(t.resourceId).append(" ").append(t.dateTime).append("\n"); 
          else
            data.append("no talk entity with the name " + (String)req.getParameter("talkname"));
     }


      if ( "getTalkByKey".equals(action) )
     {
          Talk t  = GQDataStore.GetTalkByKey((String)req.getParameter("key"));
          if ( t != null )
            data.append(t.talkName).append(" ").append(t.resourceId).append(" ").append(t.dateTime).append("\n"); 
          else
            data.append("no talk entity with the name " + (String)req.getParameter("talkname"));
     }



      
     

    resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
    resp.getWriter().println(data.toString());



  }
 


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
   
    
    /*  
    GQDataStore.TestNewEmployee();

    StringBuilder data = new StringBuilder();
    Entity emp = GQDataStore.getEmployeeByKey(1);

    //data.append("Name:").append(.getKey().append("\n");
    data.append("firstName:").append(emp.getProperty("firstName"));
    
    data.append("key").append(KeyFactory.keyToString(emp.getKey()));
    data.append("\n key.toString").append(emp.getKey().toString());
    data.append("\n\n");;

    Map<String,Object> props = emp.getProperties();

    for ( Map.Entry<String, Object> entry : props.entrySet() )
    {
      String key = entry.getKey();
      data.append(key).append(",");
    }
    */

    resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
    //resp.getWriter().println(data.toString());



	}
}