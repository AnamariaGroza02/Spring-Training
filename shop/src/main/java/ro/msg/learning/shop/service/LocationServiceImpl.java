package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location getLocationById(UUID id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return location.get();
        }
        return new Location();
    }
}
