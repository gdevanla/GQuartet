package com.gquartet.data;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.*;

import java.util.*;
import java.lang.StringBuilder;



public class Slide
{
  String key;
  long SlideNo;

  //attributes that are populated in some instances
  List<Question> questions = new ArrayList<Question>();


  public static Slide GetSlide(Entity entity)
  {
      Slide q = new Slide();
      q.key = KeyFactory.keyToString(entity.getKey());
      q.SlideNo = (Long)entity.getProperty("SlideNo");

      return q;
  }

  public static Entity GetEntity(String parentKey, long  slideNo)
  {
    Entity slideEntity = new Entity("Slide", KeyFactory.stringToKey(parentKey) );
    slideEntity.setProperty("SlideNo", slideNo);

    return slideEntity;
  }

  public String toString()
  {
    StringBuilder b = new StringBuilder();
    Key k = KeyFactory.stringToKey(key);
    b.append( "Slide (").append(k.getId()).append(") : ").append( "SlideNo=" ).append(SlideNo);
    b.append(" ParentInfo = ").append(k.getParent().toString());

    b.append("\n");
    for ( Question q : questions )
    {
      b.append("\t").append(q.toString()).append("\n");
    }

    return b.toString();
  }

}


