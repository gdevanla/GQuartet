package com.gquartet.data;

import java.util.Date;
import java.util.*;
import com.google.appengine.api.datastore.*;


public class Talk
{
  public String key;
  public String resourceId;
  public String talkName;
  public Date dateTime;

  public long activeSlideNo=0;
  public Date slideChangedDate = new Date();

  //attributes that are populated in some scenarios
  public List<Slide> slides = new ArrayList<Slide>();

  public static Entity GetEntity( String resourceId, Date sessionDate, String talkName, long slideNo, Date slideChanged)
  {
    
    Entity talk = new Entity("Talk");
    talk.setProperty("Date", new Date());
    talk.setProperty("DocumentId", resourceId);
    talk.setProperty("Name", talkName);

    talk.setProperty("ActiveSlideNo", slideNo);
    talk.setProperty("SlideChanged", slideChanged);
    
    return talk;
  }





  public static Talk GetTalk(Entity entity)
  {
       Talk t = new Talk();
       t.key = KeyFactory.keyToString(entity.getKey());
       t.resourceId = (String)entity.getProperty("DocumentId");
       t.talkName = (String)entity.getProperty("Name");
       t.dateTime = (Date)entity.getProperty("Date");
       t.activeSlideNo = (Long)entity.getProperty("ActiveSlideNo");
       t.slideChangedDate = (Date)entity.getProperty("SlideChanged");

       return t;
  }

  public static void UpdateActiveSlide(Entity e, long slideNo)
  {
      if ( e.getKind().equals("Talk") )
      {
        e.setProperty("ActiveSlideNo", slideNo);
        e.setProperty("SlideChanged", new Date());

      }
  }










  
  
}
