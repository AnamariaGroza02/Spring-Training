package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.Location;

import java.util.UUID;

public interface LocationService {
    public Location getLocationById(UUID id);
}
