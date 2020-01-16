package com.android.teamnovelvn.sakuranovel.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.android.teamnovelvn.sakuranovel.Activity.ThongTinNovel;
import com.android.teamnovelvn.sakuranovel.Entity.DanhSachTruyen;
import com.android.teamnovelvn.sakuranovel.Entity.LightNovel;
import com.android.teamnovelvn.sakuranovel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thanh on 11/22/2016.
 */

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    private ArrayList<DanhSachTruyen> arrayList = new ArrayList<>();
    private ArrayList<String> tenTruyen;
    private ArrayList<String> ten;
    private AdapterTruyen adapterTruyen;
    private GridView gridviewTruyen;
    private DatabaseReference mData;
    private ProgressBar pg;
    private ProgressDialog pro;
    int count;

    public TestRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }
     Context mContext;

    public TestRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = null;
        if (viewType == TYPE_HEADER)
        {
            view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card_big,parent,false);
        }

//        switch (viewType) {
//            case TYPE_HEADER: {
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.list_item_card_big, parent, false);
//                return new RecyclerView.ViewHolder(view) {
//                };
//            }
//            case TYPE_CELL: {
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.list_item_card_small, parent, false);
//                return new RecyclerView.ViewHolder(view) {
//                };
//            }
//        }
//        TextView t = (TextView) view.findViewById(R.id.asdf);
//        t.setText("afawfawfafawawfawfawfawffwafawfa");
        //return null;
        mData = FirebaseDatabase.getInstance().getReference();
        pg = (ProgressBar)view.findViewById(R.id.progressBar2);
        tenTruyen = new ArrayList<String>();
        gridviewTruyen = (GridView) view.findViewById(R.id.gridViewTruyen);
        adapterTruyen = new AdapterTruyen ((Activity) parent.getContext(),R.layout.viewsingleline_layout,arrayList);
        gridviewTruyen.setAdapter(adapterTruyen);

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // do ta chưa có truy cập vào phần tử nào trên cây thư mục nên ta phải tạo for và truy cập đến từng phần tử con
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    tenTruyen.add(String.valueOf(dsp.getKey().toString()));
                    count++;
                }
                // vì mảng ArrayList tenTruyen khi ra khỏi sự kiện addValue ( lấy dữ liệu ) kiểu ( private ) này thì mảng tenTruyen sẽ không nhận được dữ liệu nào (vì là private) dù ở trong vòng lặp for đã nhận dữ liệu rồi nên tạo 1 phương thức truyền tất cả dữ liệu tenTruyen khi lấy đc

                Collections.shuffle(tenTruyen);
                laytenTruyen(tenTruyen);
                pg.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        gridviewTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), String.valueOf(tenTruyen.get(position).toString()), Toast.LENGTH_LONG).show();
                Intent i = new Intent(parent.getContext(), ThongTinNovel.class);
                i.putExtra("TRUYEN",tenTruyen.get(position).toString());
                view.getContext().startActivity(i);
            }
        });


        return new RecyclerView.ViewHolder(view){};
    }
    private void addControls(){
//        mData = FirebaseDatabase.getInstance().getReference();
//        tenTruyen = new ArrayList<String>();
//        gridviewTruyen = (GridView) view.findViewById(R.id.gridViewTruyen);
//        adapterTruyen = new AdapterTruyen(RootActivity.class,R.layout.viewsingleline_layout,arrayList);
//        gridviewTruyen.setAdapter(adapterTruyen);
    }
    public void addEvent(){
        // Lấy tất cả tên truyện
    }
    public void laytenTruyen(ArrayList<String> tenTruyen){
        ten = new ArrayList<>();
        // add tất cả dữ liệu khi được nhận ở sự kiện trên
        ten.addAll(tenTruyen);
        // mảng ten hiện tại đã chứa 20 tên truyện
        // nên tạo 1 vòng lặp for vào cây thư mục con từng truyện, lấy hình của truyện đó ra và set lên gridView
        for (int i = 0; i<ten.size(); i++){

            final String hin = ten.get(i).toString();
            // truy cập đến phần tử con của truyện đó
            mData.child(hin).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // khi lấy dữ liệu nó sẽ gom tất cả dữ liệu trong cây thư mục con đó cùng lúc
                    // nên tạo 1 biến để convert dữ liệu lại thành object
                    LightNovel ln = dataSnapshot.getValue(LightNovel.class);
                    arrayList.add(new DanhSachTruyen(ln.getHinh(),hin));
                    adapterTruyen.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }
}
