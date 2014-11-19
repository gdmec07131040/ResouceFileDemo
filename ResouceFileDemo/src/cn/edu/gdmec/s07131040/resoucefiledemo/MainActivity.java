package cn.edu.gdmec.s07131040.resoucefiledemo;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView mytv1,mytv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytv1=(TextView) findViewById(R.id.textView1);
        mytv2=(TextView) findViewById(R.id.textView2);
    }
    public void ReadRaw(View v){
    	InputStream is=null;
    	is=getResources().openRawResource(R.raw.raw_file);
    	try {
			byte[] reader=new byte[is.available()];
			while(is.read(reader)!=-1){
				mytv1.setText(new String(reader,"GBK"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void ReadXml(View v){
    	XmlPullParser myXpp=getResources().getXml(R.xml.people);
    	String msg="";
    	try {
			while(myXpp.next()!=XmlPullParser.END_DOCUMENT){
				String nodeName=myXpp.getName();
				if(nodeName!=null&&nodeName.equals("person")){
					int attrCount=myXpp.getAttributeCount();
					for(int i=0;i<attrCount;i++){
						String attrName=myXpp.getAttributeName(i);
						if(attrName!=null&&attrName.equals("name")){
							msg+="姓名"+myXpp.getAttributeValue(i);
						}else if(attrName!=null&&attrName.equals("age")){
							msg+="年龄"+myXpp.getAttributeValue(i);
						}else if(attrName!=null&&attrName.equals("height")){
							msg+="身高"+myXpp.getAttributeValue(i);
						}
						
					}
					msg+="\n";
				}
			}
			mytv2.setText(msg);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
