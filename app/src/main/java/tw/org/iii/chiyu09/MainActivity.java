package tw.org.iii.chiyu09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView lv99;
    private SimpleAdapter adapter1;
    private LinkedList<HashMap<String,Object>> myData; // 資料集(很多筆資料)
    private String[] fromD = {"name","content","pic"};
    private int[] toL = {R.id.item_tv, R.id.item_content, R.id.item_img};

    private int[] imgs = {R.drawable.b1, R.drawable.b2, R.drawable.b3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv99 = (ListView)findViewById(R.id.listView);
        initListView();
    }

    private  void initListView(){
        myData = new LinkedList<>();
        HashMap<String,Object> row0;

        // 擺資料
        row0 = new HashMap<>();
        row0.put(fromD[0],"Chiyu");
        row0.put(fromD[1],"So Smart!!!");
        row0.put(fromD[2],imgs[0]);
        myData.add(row0);

        for(int i = 0;i<20;i++){
            row0 = new HashMap<>();
            row0.put(fromD[0],"Chiyu" + i);
            row0.put(fromD[1],"So Smart!!!");
            row0.put(fromD[2],imgs[(int)(Math.random()*3)]);
            myData.add(row0);
        }

        // 用 adapter 抓資料
        adapter1 = new SimpleAdapter(this, myData, R.layout.layout_item,fromD,toL);
        // 把 adapter 餵給 ListView @ activity_main
        lv99.setAdapter(adapter1);

        lv99.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("chiyu", "click : " + position);
            }
        });

        lv99.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("chiyu","long : " + position);
                delItem(position);
                // 會又觸發 setOnItemClickListener
//                return false;
                // 不會觸發 setOnItemClickListener
                return true;
            }
        });

    }

    void delItem(int pos){
        myData.remove(pos);
        // 資料集 的任何異動 只要通知 adapter 畫面就會改好
        adapter1.notifyDataSetChanged();
        Log.v("chiyu",pos + " Delete");
    }


}
