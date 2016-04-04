package br.com.stormsecurity.mobicine.manager;

import android.app.Application;
import android.app.Service;

import com.devbrackets.android.exomedia.manager.EMPlaylistManager;

import br.com.stormsecurity.mobicine.App;
import br.com.stormsecurity.mobicine.data.MediaItem;
import br.com.stormsecurity.mobicine.service.AudioService;


/**
 * A PlaylistManager that extends the {@link EMPlaylistManager} for use with the
 *
 */
public class PlaylistManager extends EMPlaylistManager<MediaItem> {

    @Override
    protected Application getApplication() {
        return App.getApplication();
    }

    @Override
    protected Class<? extends Service> getMediaServiceClass() {
        return AudioService.class;
    }
}
