package com.kakao.kakaolink.v2.network;

import android.net.Uri;

import com.kakao.common.IConfiguration;
import com.kakao.common.PhaseInfo;
import com.kakao.network.ServerProtocol;
import com.kakao.network.storage.ImageDeleteRequest;

/**
 * @author kevin.kang. Created on 2017. 3. 20..
 */

class LinkImageDeleteRequest extends ImageDeleteRequest {
    LinkImageDeleteRequest(final PhaseInfo phaseInfo, IConfiguration configuration, final String imageUrl, final String imageToken) {
        super(phaseInfo, configuration, imageUrl, imageToken);
    }

    @Override
    public Uri.Builder getUriBuilder() {
        Uri.Builder builder = super.getUriBuilder();
        builder.path(ServerProtocol.LINK_IMAGE_DELETE_PATH);
        return builder;
    }
}
