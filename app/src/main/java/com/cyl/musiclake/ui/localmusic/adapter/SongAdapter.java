package com.cyl.musiclake.ui.localmusic.adapter;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.musiclake.R;
import com.cyl.musiclake.api.GlideApp;
import com.cyl.musiclake.data.model.Music;
import com.cyl.musiclake.utils.CoverLoader;
import com.cyl.musiclake.utils.FileUtils;

import java.util.List;

/**
 * 功能：本地歌曲item
 * 作者：yonglong on 2016/8/8 19:44
 * 邮箱：643872807@qq.com
 * 版本：2.5
 */
public class SongAdapter extends BaseQuickAdapter<Music, BaseViewHolder> {
//        RecyclerView.Adapter<SongAdapter.ItemHolder> {

    public SongAdapter(List<Music> musicInfos) {
        super(R.layout.item_music, musicInfos);
    }

    @Override
    protected void convert(BaseViewHolder holder, Music item) {
        String url;
        if (item.getType() == Music.Type.LOCAL && item.getAlbumId() != -1) {
            url = CoverLoader.getInstance().getCoverUri(mContext, item.getAlbumId());
        } else {
            url = item.getCoverUri();
        }
        GlideApp.with(mContext)
                .asBitmap()
                .load(url)
                .error(R.drawable.default_cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) holder.getView(R.id.iv_cover));

        holder.setText(R.id.tv_title, FileUtils.getTitle(item.getTitle()));
        holder.setText(R.id.tv_artist, FileUtils.getArtistAndAlbum(item.getArtist(), item.getAlbum()));
//        if (PlayManager.getPlayingMusic() != null && PlayManager.getPlayingMusic().equals(localItem)) {
//            holder.v_playing.setVisibility(View.VISIBLE);
//        } else {
//            holder.v_playing.setVisibility(View.GONE);
//        }
        holder.addOnClickListener(R.id.iv_more);
    }


}