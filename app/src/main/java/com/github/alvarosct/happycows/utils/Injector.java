package com.github.alvarosct.happycows.utils;

import com.github.alvarosct.happycows.data.source.DataSourceRepository;
import com.github.alvarosct.happycows.data.source.local.DataSourceLocal;
import com.github.alvarosct.happycows.data.source.remote.DataSourceRemote;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public class Injector {

    public static DataSourceRepository provideRepository() {
        return DataSourceRepository.getInstance(DataSourceRemote.getInstance(),
                DataSourceLocal.getInstance());
    }

}
