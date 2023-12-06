package io.nuptse.pasal.mapstruct.mappers.inventory;

import io.nuptse.pasal.mapstruct.dto.inventory.InventoryGetDto;
import io.nuptse.pasal.mapstruct.dto.inventory.InventoryListDto;
import io.nuptse.pasal.mapstruct.dto.inventory.InventoryPostDto;
import io.nuptse.pasal.mapstruct.dto.inventory.InventorySlimDto;
import io.nuptse.pasal.mapstruct.mappers.LocationMapper;
import io.nuptse.pasal.models.inventory.Inventory;
import io.nuptse.pasal.services.LocationService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationMapper locationMapper;

    @Override
    public InventoryGetDto toGetDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryGetDto.InventoryGetDtoBuilder inventoryGetDto = InventoryGetDto.builder();

        inventoryGetDto.id( inventory.getId() );
        inventoryGetDto.name( inventory.getName() );
        inventoryGetDto.warehouse( locationMapper.locationToLocationSlimDto( inventory.getWarehouse() ) );
        inventoryGetDto.deleted( inventory.getDeleted() );
        inventoryGetDto.active( inventory.getActive() );

        return inventoryGetDto.build();
    }

    @Override
    public Inventory toEntity(InventoryPostDto inventoryPostDto) {
        if ( inventoryPostDto == null ) {
            return null;
        }

        Inventory.InventoryBuilder<?, ?> inventory = Inventory.builder();

        inventory.name( inventoryPostDto.name() );
        inventory.warehouse( locationService.idToEntity( inventoryPostDto.warehouse() ) );
        inventory.active( inventoryPostDto.active() );
        inventory.deleted( inventoryPostDto.deleted() );

        return inventory.build();
    }

    @Override
    public Inventory toUpdateEntity(InventoryPostDto inventoryPostDto, Inventory inventory) {
        if ( inventoryPostDto == null ) {
            return inventory;
        }

        inventory.setName( inventoryPostDto.name() );
        inventory.setWarehouse( locationService.idToEntity( inventoryPostDto.warehouse() ) );
        inventory.setActive( inventoryPostDto.active() );
        inventory.setDeleted( inventoryPostDto.deleted() );

        return inventory;
    }

    @Override
    public InventorySlimDto toSlimDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = inventory.getId();
        name = inventory.getName();

        InventorySlimDto inventorySlimDto = new InventorySlimDto( id, name );

        return inventorySlimDto;
    }

    @Override
    public InventoryListDto toListDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = inventory.getId();
        name = inventory.getName();

        InventoryListDto inventoryListDto = new InventoryListDto( id, name );

        return inventoryListDto;
    }
}
