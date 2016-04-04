package br.com.stormsecurity.mobicine.data;

import com.devbrackets.android.exomedia.manager.EMPlaylistManager;

import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AudioItems;

/**
 * A custom {@link com.devbrackets.android.exomedia.manager.EMPlaylistManager.PlaylistItem}
 * to hold the information pertaining to the audio and video items
 */
public class MediaItem implements EMPlaylistManager.PlaylistItem {

    private String artworkUrl;
    private String mediaUrl;
    private String title;
    boolean isAudio;

    public MediaItem(AudioItems.AudioItem audioItem) {
        artworkUrl = audioItem.getArtworkUrl();
        mediaUrl = audioItem.getMediaUrl();
        title = audioItem.getTitle();
        isAudio = true;
    }

    public MediaItem(VideoItem videoItem) {
        artworkUrl = null;
        mediaUrl = videoItem.getMediaUrl();
        title = videoItem.getTitulo();
        isAudio = false;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getPlaylistId() {
        return 0;
    }

    @Override
    public EMPlaylistManager.MediaType getMediaType() {
        return isAudio ? EMPlaylistManager.MediaType.AUDIO : EMPlaylistManager.MediaType.VIDEO;
    }

    @Override
    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public String getDownloadedMediaUri() {
        return null;
    }

    @Override
    public String getThumbnailUrl() {
        return artworkUrl;
    }

    @Override
    public String getArtworkUrl() {
        return artworkUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAlbum() {
        return "ExoMedia Demo";
    }

    @Override
    public String getArtist() {
        return "Unknown Artist";
    }
}