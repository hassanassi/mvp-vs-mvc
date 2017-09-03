package project.demo.model.service;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import project.demo.model.Item;


/**
 * Created by hassan on 9/2/2017.
 */

public class Parser {

    /**
     *
     * @param response
     * @return items from xml string
     */
   public ArrayList<Item> parseXml(String response){
       ArrayList<Item> items=new ArrayList<>();


       XmlPullParser parser = Xml.newPullParser();

       try {
           parser.setInput( new StringReader( response) );

           //while not end of document
           while (parser.next() != XmlPullParser.END_DOCUMENT) {
               int eventType = parser.getEventType();

               String tagName = parser.getName();
               if(tagName == null)
                   continue;


               if(eventType ==  XmlPullParser.START_TAG) {
                   if(tagName.equalsIgnoreCase("item")) {
                       items.add(readItem(parser));
                   }
               }

           }

       } catch (XmlPullParserException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

      return items;
   }



    /**
     * parse the content of item tag
     * and retrieve the content of description ,title, url inside it as an Item object
     * @param parser
     * @return
     */
    private Item readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "item");

        String title="";
        String description="";
        String url="";

        //keep looping until reaching end of item tag
        while (parser.next()!=XmlPullParser.END_TAG || !"item".equalsIgnoreCase(parser.getName()) || parser.getEventType()==XmlPullParser.END_DOCUMENT){

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            if(name==null) continue;

            if(name.equals("title")){
                //read the value of title tag
                title=readTag(parser,"title");
            }else if(name.equals("description")){
                description=readTag(parser,"description");
            }else if(name.equals("thumbnail")){
                //read the url inside the thumbail tag
                url=parser.getAttributeValue(null,"url");
            }

        }

        return new Item(title,description,url);
    }


    /**
     *
     * @param parser
     * @param tag
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readTag(XmlPullParser parser,String tag) throws IOException, XmlPullParserException {
        //throws exception if not identical tag
        parser.require(XmlPullParser.START_TAG, null, tag);
        String tagText = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, tag);
        return tagText;
    }


    /**
     * extract the value of tag (title and description)
     * @param parser
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String value = "";
        if (parser.next() == XmlPullParser.TEXT) {
            value = parser.getText();
            parser.nextTag();
        }
        return value;
    }
}
