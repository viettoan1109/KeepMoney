package vn.trungkma.money.ui.adapter;

import static vn.trungkma.money.ui.main.MainActivity.getSelectList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import vn.trungkma.money.R;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.ItemMediaBinding;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    public MediaAdapter.OnItemClickedListener onItemClickedListener;
    public List<FileData> list = new LinkedList<>();
    public List<FileData> listSearch = new LinkedList<>();
    private Context context;

    public void setList(List<FileData> list) {
        this.list = list;
        filter("");

    }

    public List<FileData> getList() {
        return list;
    }

    public MediaAdapter(Context context) {
        this.context = context;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        listSearch.clear();
        if (charText.length() == 0){
            listSearch.addAll(list);
        } else {
            for (FileData fileData : list){
                if (fileData.getFileName().toLowerCase(Locale.getDefault()).contains(charText)){
                    listSearch.add(fileData);

                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMediaBinding binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(listSearch.get(holder.getAdapterPosition()).getFilePath()).into(holder.binding.imgItemThumblia);
        holder.binding.tvTitle.setText(listSearch.get(holder.getAdapterPosition()).getFileName());


        if (getSelectList().size() > 0) {
            for (int i = 0; i < listSearch.size(); i++) {
                for (int j = 0; j < getSelectList().size(); j++) {
                    if (listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName())) {
                        holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_true);
                        listSearch.get(i).setChecked(true);
                    }
                }
            }
        }

        holder.binding.imgItemCheck.setImageResource(listSearch.get(holder.getAdapterPosition()).isChecked() ? R.drawable.ic_check_true : R.drawable.ic_check_false);
        holder.binding.imgItemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickedListener.onItemClick(holder.getAdapterPosition());
                holder.binding.imgItemCheck.setImageResource(listSearch.get(holder.getAdapterPosition()).isChecked() ? R.drawable.ic_check_true : R.drawable.ic_check_false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listSearch == null ? 0 : listSearch.size();
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClick(int position);

        void onFolderClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemMediaBinding binding;

        public ViewHolder(@NonNull ItemMediaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
