package br.com.mobicare.utils;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ParametersService {

    public String getParameterAsString(String key) {
        final Optional<ParametersEntity> parameter = findParameter(key);
        if (parameter.isPresent()) {
            return String.valueOf(parameter.get().value);
        }
        return null;
    }

    private Optional<ParametersEntity> findParameter(String key) {
        return ParametersEntity.findByKey(key);
    }

}
