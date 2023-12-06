package io.nuptse.pasal.mapstruct.mappers.inventory;

import io.nuptse.pasal.mapstruct.dto.inventory.level.InventoryLevelGetDto;
import io.nuptse.pasal.mapstruct.dto.inventory.level.InventoryLevelListDto;
import io.nuptse.pasal.mapstruct.dto.inventory.level.InventoryLevelPostDto;
import io.nuptse.pasal.mapstruct.dto.inventory.level.InventoryLevelSlimDto;
import io.nuptse.pasal.mapstruct.dto.location.LocationSlimDto;
import io.nuptse.pasal.models.core.Location;
import io.nuptse.pasal.models.inventory.InventoryItem;
import io.nuptse.pasal.models.inventory.InventoryLevel;
import io.nuptse.pasal.services.LocationService;
import io.nuptse.pasal.services.inventory.InventoryItemService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class InventoryLevelMapperImpl implements InventoryLevelMapper {

    @Autowired
    private LocationService locationService;
    @Autowired
    private InventoryItemService inventoryItemService;
    @Autowired
    private InventoryItemMapper inventoryItemMapper;

    @Override
    public InventoryLevelGetDto toGetDto(InventoryLevel level) {
        if ( level == null ) {
            return null;
        }

        InventoryLevelGetDto.InventoryLevelGetDtoBuilder inventoryLevelGetDto = InventoryLevelGetDto.builder();

        inventoryLevelGetDto.id( level.getId() );
        inventoryLevelGetDto.inventoryItem( inventoryItemMapper.toSlimDto( level.getInventoryItem() ) );
        inventoryLevelGetDto.minQuantity( level.getMinQuantity() );
        inventoryLevelGetDto.reorderQuantity( level.getReorderQuantity() );
        inventoryLevelGetDto.maxQuantity( level.getMaxQuantity() );
        inventoryLevelGetDto.forecastQuantity( level.getForecastQuantity() );
        inventoryLevelGetDto.forecastPeriodDays( level.getForecastPeriodDays() );
        inventoryLevelGetDto.expectedLeadTimeDays( level.getExpectedLeadTimeDays() );
        inventoryLevelGetDto.replenishmentPeriodDays( level.getReplenishmentPeriodDays() );
        inventoryLevelGetDto.demandTimePeriodDays( level.getDemandTimePeriodDays() );
        inventoryLevelGetDto.comment( level.getComment() );
        inventoryLevelGetDto.binLocation( locationToLocationSlimDto( level.getBinLocation() ) );
        inventoryLevelGetDto.replenishmentLocation( locationToLocationSlimDto( level.getReplenishmentLocation() ) );
        inventoryLevelGetDto.internalLocation( locationToLocationSlimDto( level.getInternalLocation() ) );
        inventoryLevelGetDto.deleted( level.getDeleted() );
        inventoryLevelGetDto.active( level.getActive() );

        return inventoryLevelGetDto.build();
    }

    @Override
    public InventoryLevel toEntity(InventoryLevelPostDto levelPostDto) {
        if ( levelPostDto == null ) {
            return null;
        }

        InventoryLevel.InventoryLevelBuilder<?, ?> inventoryLevel = InventoryLevel.builder();

        inventoryLevel.inventoryItem( inventoryItemService.idToEntity( levelPostDto.inventoryItem() ) );
        inventoryLevel.minQuantity( levelPostDto.minQuantity() );
        inventoryLevel.reorderQuantity( levelPostDto.reorderQuantity() );
        inventoryLevel.maxQuantity( levelPostDto.maxQuantity() );
        inventoryLevel.forecastQuantity( levelPostDto.forecastQuantity() );
        inventoryLevel.forecastPeriodDays( levelPostDto.forecastPeriodDays() );
        inventoryLevel.expectedLeadTimeDays( levelPostDto.expectedLeadTimeDays() );
        inventoryLevel.replenishmentPeriodDays( levelPostDto.replenishmentPeriodDays() );
        inventoryLevel.demandTimePeriodDays( levelPostDto.demandTimePeriodDays() );
        inventoryLevel.comment( levelPostDto.comment() );
        inventoryLevel.binLocation( locationService.idToEntity( levelPostDto.binLocation() ) );
        inventoryLevel.replenishmentLocation( locationService.idToEntity( levelPostDto.replenishmentLocation() ) );
        inventoryLevel.internalLocation( locationService.idToEntity( levelPostDto.internalLocation() ) );
        inventoryLevel.active( levelPostDto.active() );
        inventoryLevel.deleted( levelPostDto.deleted() );

        return inventoryLevel.build();
    }

    @Override
    public InventoryLevel toUpdateEntity(InventoryLevelPostDto levelPostDto, InventoryLevel level) {
        if ( levelPostDto == null ) {
            return level;
        }

        level.setInventoryItem( inventoryItemService.idToEntity( levelPostDto.inventoryItem() ) );
        level.setMinQuantity( levelPostDto.minQuantity() );
        level.setReorderQuantity( levelPostDto.reorderQuantity() );
        level.setMaxQuantity( levelPostDto.maxQuantity() );
        level.setForecastQuantity( levelPostDto.forecastQuantity() );
        level.setForecastPeriodDays( levelPostDto.forecastPeriodDays() );
        level.setExpectedLeadTimeDays( levelPostDto.expectedLeadTimeDays() );
        level.setReplenishmentPeriodDays( levelPostDto.replenishmentPeriodDays() );
        level.setDemandTimePeriodDays( levelPostDto.demandTimePeriodDays() );
        level.setComment( levelPostDto.comment() );
        level.setBinLocation( locationService.idToEntity( levelPostDto.binLocation() ) );
        level.setReplenishmentLocation( locationService.idToEntity( levelPostDto.replenishmentLocation() ) );
        level.setInternalLocation( locationService.idToEntity( levelPostDto.internalLocation() ) );
        level.setActive( levelPostDto.active() );
        level.setDeleted( levelPostDto.deleted() );

        return level;
    }

    @Override
    public InventoryLevelSlimDto toSlimDto(InventoryLevel level) {
        if ( level == null ) {
            return null;
        }

        String lotNumber = null;
        Long id = null;

        lotNumber = levelInventoryItemLotNumber( level );
        id = level.getId();

        InventoryLevelSlimDto inventoryLevelSlimDto = new InventoryLevelSlimDto( id, lotNumber );

        return inventoryLevelSlimDto;
    }

    @Override
    public InventoryLevelListDto toListDto(InventoryLevel level) {
        if ( level == null ) {
            return null;
        }

        String lotNumber = null;
        Long id = null;

        lotNumber = levelInventoryItemLotNumber( level );
        id = level.getId();

        InventoryLevelListDto inventoryLevelListDto = new InventoryLevelListDto( id, lotNumber );

        return inventoryLevelListDto;
    }

    protected LocationSlimDto locationToLocationSlimDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationSlimDto locationSlimDto = new LocationSlimDto();

        locationSlimDto.setId( location.getId() );
        locationSlimDto.setName( location.getName() );

        return locationSlimDto;
    }

    private String levelInventoryItemLotNumber(InventoryLevel inventoryLevel) {
        if ( inventoryLevel == null ) {
            return null;
        }
        InventoryItem inventoryItem = inventoryLevel.getInventoryItem();
        if ( inventoryItem == null ) {
            return null;
        }
        String lotNumber = inventoryItem.getLotNumber();
        if ( lotNumber == null ) {
            return null;
        }
        return lotNumber;
    }
}
