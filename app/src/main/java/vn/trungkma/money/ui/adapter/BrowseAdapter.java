package vn.trungkma.money.ui.adapter;

import static vn.trungkma.money.common.Constant.ARCHIVE_7ZIP;
import static vn.trungkma.money.common.Constant.ARCHIVE_GZIP;
import static vn.trungkma.money.common.Constant.ARCHIVE_RAR;
import static vn.trungkma.money.common.Constant.ARCHIVE_ZIP;
import static vn.trungkma.money.common.Constant.IMAGE_JPEG;
import static vn.trungkma.money.common.Constant.IMAGE_JPG;
import static vn.trungkma.money.common.Constant.IMAGE_PNG;
import static vn.trungkma.money.common.Constant.IMAGE_WEBP;
import static vn.trungkma.money.common.Constant.TYPE_APK;
import static vn.trungkma.money.common.Constant.TYPE_DOC_X;
import static vn.trungkma.money.common.Constant.TYPE_MP3;
import static vn.trungkma.money.common.Constant.TYPE_PDF;
import static vn.trungkma.money.common.Constant.TYPE_PPTX;
import static vn.trungkma.money.common.Constant.TYPE_TXT;
import static vn.trungkma.money.common.Constant.TYPE_XLSX;
import static vn.trungkma.money.common.Constant.TYPE_mp4;
import static vn.trungkma.money.utils.Utils.convertByte;
import static vn.trungkma.money.utils.Utils.getDate;

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
import vn.trungkma.money.databinding.ItemFolderBrowseBinding;

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.ViewHolder> {

    public BrowseAdapter.OnItemClickedListener onItemClickedListener;
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

    public BrowseAdapter(Context context) {
        this.context = context;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listSearch.clear();
        if (charText.length() == 0) {
            listSearch.addAll(list);
        } else {
            for (FileData fileData : list) {
                if (fileData.getFileName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listSearch.add(fileData);

                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFolderBrowseBinding binding = ItemFolderBrowseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listSearch.get(holder.getAdapterPosition()).getFolderSize() > 0) {
            holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_folder);

        } else {
            if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_DOC_X)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_word);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_PDF)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_pdf);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_APK)) {
                Glide.with(context).load(listSearch.get(holder.getAdapterPosition()).getFilePath()).placeholder(R.drawable.img_file_apk).into(holder.binding.imgBrowseFolder);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_TXT)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_txt);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_PPTX)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_ppt);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_XLSX)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_xlsx);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(ARCHIVE_ZIP) || listSearch.get(holder.getAdapterPosition()).getFileName().contains(ARCHIVE_RAR)
                    || listSearch.get(holder.getAdapterPosition()).getFileName().contains(ARCHIVE_7ZIP) || listSearch.get(holder.getAdapterPosition()).getFileName().contains(ARCHIVE_GZIP)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_archive);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_MP3)) {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_music);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(IMAGE_JPG) || listSearch.get(holder.getAdapterPosition()).getFileName().contains(IMAGE_PNG)
                    || listSearch.get(holder.getAdapterPosition()).getFileName().contains(IMAGE_WEBP) || listSearch.get(holder.getAdapterPosition()).getFileName().contains(IMAGE_JPEG)) {
                Glide.with(context).load(listSearch.get(holder.getAdapterPosition()).getFilePath()).into(holder.binding.imgBrowseFolder);
            } else if (listSearch.get(holder.getAdapterPosition()).getFileName().contains(TYPE_mp4)) {
                Glide.with(context).load(listSearch.get(holder.getAdapterPosition()).getFilePath()).into(holder.binding.imgBrowseFolder);
            } else {
                holder.binding.imgBrowseFolder.setImageResource(R.drawable.img_file_no_name);

            }
        }


        holder.binding.tvBrowseTitle.setText(listSearch.get(holder.getAdapterPosition()).getFileName());
        if (listSearch.get(holder.getAdapterPosition()).getFolderSize() > 0) {
            holder.binding.tvBrowseContent.setText(listSearch.get(holder.getAdapterPosition()).getFolderSize() + " item(s)" + " . "
                    + getDate(String.valueOf(listSearch.get(holder.getAdapterPosition()).getFileTime())));
        } else {
            holder.binding.tvBrowseContent.setText(convertByte(listSearch.get(holder.getAdapterPosition()).getFileSize()) + " . "
                    + getDate(String.valueOf(listSearch.get(holder.getAdapterPosition()).getFileTime())));
        }

        holder.binding.constraintBrowseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.onFolderClick(holder.getAdapterPosition());
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
        ItemFolderBrowseBinding binding;

        public ViewHolder(@NonNull ItemFolderBrowseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
