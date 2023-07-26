package ro.msg.learning.shop.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.strategy.FindLocationStrategy;
import ro.msg.learning.shop.strategy.FindMostAbundantLocationStrategy;
import ro.msg.learning.shop.strategy.FindSingleLocationStrategy;

@Configuration
public class StrategyConfiguration {


    @Bean
    @ConditionalOnProperty(prefix = "shop", name = "strategy", havingValue = "most-abundant")
    public FindLocationStrategy getMostAbundantLocationStrategy(final ProductService productService,
                                                                final LocationService locationService,
                                                                final StockService stockService) {
        return new FindMostAbundantLocationStrategy(productService, locationService, stockService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "shop", name = "strategy", havingValue = "single-location")
    public FindLocationStrategy getSingleLocationStrategy(final ProductService productService,
                                                                final LocationService locationService,
                                                                final StockService stockService) {
        return new FindSingleLocationStrategy(productService, locationService, stockService);
    }
}
