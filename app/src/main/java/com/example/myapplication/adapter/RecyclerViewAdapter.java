package com.example.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.TambahKontakActivity;
import com.example.myapplication.common.DataListListener;
import com.example.myapplication.database.db.MyApp;
import com.example.myapplication.database.entity.Kontak;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Kontak> dataList = new ArrayList<>();
    private DataListListener listener;

    public void setData(List<Kontak> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Kontak data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(Kontak data) {
        int position = -1;

        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }

    public void removeData(Kontak data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getId() == data.getId()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }
    public void setRemoveListener(DataListListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView nama, nomor;
//        private ImageView btnHapus;
        private Kontak data;
        private DataListListener listener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvNama);
            nomor = itemView.findViewById(R.id.tvNomor);
//            btnHapus = itemView.findViewById(R.id.btn);

//            btnHapus.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void bind(Kontak data, DataListListener listener) {
            this.data = data;
            this.listener = listener;

            nama.setText(data.getNama());
            nomor.setText(data.getNomor());

        }

        @Override
        public void onClick(View view) {
//            if (view.getId() == R.id.btnHapus) {
//
//                MyApp.getInstance().getDatabase().userDao().delete(data);
//                listener.onRemoveClick(data);
//                Toast.makeText(itemView.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//
//            } else
                if (view.getId() == R.id.item_list) {

                Intent intent = new Intent(itemView.getContext(), TambahKontakActivity.class);
                intent.putExtra(TambahKontakActivity.TAG_DATA_INTENT, data.getId());
                itemView.getContext().startActivity(intent);

            }
        }
    }

}
