package com.kakao.auth.network;

import com.kakao.common.IConfiguration;
import com.kakao.common.PhaseInfo;
import com.kakao.network.IRequest;

/**
 * @author kevin.kang. Created on 2017. 11. 30..
 */

public interface AuthorizedRequest extends IRequest {
    void setAccessToken(final String accessToken);
    void setConfiguration(final PhaseInfo phaseInfo, final IConfiguration configuration);
}
