package ru.homework.loadingcar.service.cargo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.entity.Cargo;
import ru.homework.loadingcar.service.CargoService;
import ru.homework.loadingcar.type.CargoType;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CargoServiceImpl implements CargoService {
    public List<Cargo> createCargoList(String contentString) {
        log.info("получение всего груза: '{}'", contentString);
        return CargoType.stream()
                .map(a -> Pattern.compile(a.getPattern()).matcher(contentString)
                        .results()
                        .map(s -> new Cargo(a.getSize(), a.getNumber()))
                        .toList())
                .filter(a -> !a.isEmpty())
                .flatMap(Collection::stream)
                .toList();
    }
}
