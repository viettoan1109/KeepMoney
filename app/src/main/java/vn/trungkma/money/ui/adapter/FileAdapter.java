package vn.trungkma.money.ui.adapter;

import static vn.trungkma.money.common.Constant.TYPE_7ZIP;
import static vn.trungkma.money.common.Constant.TYPE_APK;
import static vn.trungkma.money.common.Constant.TYPE_DOC_X;
import static vn.trungkma.money.common.Constant.TYPE_GZIP;
import static vn.trungkma.money.common.Constant.TYPE_MP3;
import static vn.trungkma.money.common.Constant.TYPE_PDF;
import static vn.trungkma.money.common.Constant.TYPE_PPTX;
import static vn.trungkma.money.common.Constant.TYPE_RAR;
import static vn.trungkma.money.common.Constant.TYPE_TXT;
import static vn.trungkma.money.common.Constant.TYPE_XLSX;
import static vn.trungkma.money.common.Constant.TYPE_ZIP;
import static vn.trungkma.money.common.Constant.TYPE_mp4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.data.model.Files;
import vn.trungkma.money.databinding.ItemFileBinding;
import vn.trungkma.money.utils.Utils;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    public FileAdapter.OnItemClickedListener onItemClickedListener;
    private List<Files> list = new LinkedList<>();

    private List<Files> select = new LinkedList<>();

    private Context context;

    public List<Files> getList() {
        return list;
    }

    public void setList(List<Files> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public FileAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public FileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFileBinding binding = ItemFileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (list.get(holder.getAdapterPosition()).getCategory()) {
            case TYPE_DOC_X:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_word);
                break;
            case TYPE_PDF:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_pdf);
                break;
            case TYPE_APK:
                Glide.with(context).load(list.get(holder.getAdapterPosition()).getUri()).into(holder.binding.imgItem);
                break;
            case TYPE_TXT:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_txt);
                break;
            case TYPE_PPTX:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_ppt);
                break;
            case TYPE_XLSX:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_xlsx);
                break;
            case TYPE_ZIP:
            case TYPE_RAR:
            case TYPE_7ZIP:
            case TYPE_GZIP:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_archive);
                break;
            case TYPE_MP3:
                holder.binding.imgItem.setImageResource(R.drawable.img_file_music);
                break;
            case "jpg":
            case "png":
                Glide.with(context).load(list.get(holder.getAdapterPosition()).getUri()).into(holder.binding.imgItem);
                break;
            case TYPE_mp4:
                Glide.with(context).load(list.get(holder.getAdapterPosition()).getUri()).into(holder.binding.imgItem);
                break;
            default:
                holder.binding.imgItem.setImageResource(R.drawable.img_folder);
                break;
        }


        holder.binding.tvTitle.setText(list.get(holder.getAdapterPosition()).getNameFile());
        holder.binding.tvContent.setText(Utils.convertByte(list.get(holder.getAdapterPosition()).getSize()) + " . "
                + list.get(holder.getAdapterPosition()).getTime());

        holder.binding.imgItemCheck.setImageResource(list.get(holder.getAdapterPosition()).isChecked() ? R.drawable.ic_check_true : R.drawable.ic_check_false);

        holder.binding.imgItemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.onItemClick(holder.getAdapterPosition());
                holder.binding.imgItemCheck.setImageResource(list.get(holder.getAdapterPosition()).isChecked() ? R.drawable.ic_check_true : R.drawable.ic_check_false);


            }
        });

       /* for (int i = 0; i < getSelectList().size(); i++) {
           if(selectList.get(i).isChecked()){
               holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_true);
           } else {
               holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);

           }
        }*/

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setOnItemClickedListener(FileAdapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClick(int position);


        void onFolderClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFileBinding binding;

        public ViewHolder(@NonNull ItemFileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public List<Files> getSelect() {
        List<Files> select = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                select.add(list.get(i));
            }
        }
        return select;
    }
}
