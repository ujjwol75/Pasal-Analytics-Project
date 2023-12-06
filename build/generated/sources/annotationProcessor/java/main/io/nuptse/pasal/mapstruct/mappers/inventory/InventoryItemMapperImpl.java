package io.nuptse.pasal.mapstruct.mappers.inventory;

import io.nuptse.pasal.mapstruct.dto.inventory.item.InventoryItemGetDto;
import io.nuptse.pasal.mapstruct.dto.inventory.item.InventoryItemListDto;
import io.nuptse.pasal.mapstruct.dto.inventory.item.InventoryItemPostDto;
import io.nuptse.pasal.mapstruct.dto.inventory.item.InventoryItemSlimDto;
import io.nuptse.pasal.mapstruct.dto.product.ProductSlimDto;
import io.nuptse.pasal.models.inventory.InventoryItem;
import io.nuptse.pasal.models.inventory.LotStatus;
import io.nuptse.pasal.models.product.Product;
import io.nuptse.pasal.services.inventory.InventoryService;
import io.nuptse.pasal.services.product.ProductService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class InventoryItemMapperImpl implements InventoryItemMapper {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private InventoryService inventoryService;

    @Override
    public InventoryItemGetDto toGetDto(InventoryItem inventoryItem) {
        if ( inventoryItem == null ) {
            return null;
        }

        InventoryItemGetDto.InventoryItemGetDtoBuilder inventoryItemGetDto = InventoryItemGetDto.builder();

        inventoryItemGetDto.lotStatus( inventoryItemLotStatusStatus( inventoryItem ) );
        inventoryItemGetDto.id( inventoryItem.getId() );
        inventoryItemGetDto.product( productToProductSlimDto( inventoryItem.getProduct() ) );
        inventoryItemGetDto.inventory( inventoryMapper.toSlimDto( inventoryItem.getInventory() ) );
        inventoryItemGetDto.lotNumber( inventoryItem.getLotNumber() );
        inventoryItemGetDto.expirationDate( inventoryItem.getExpirationDate() );
        inventoryItemGetDto.quantity( inventoryItem.getQuantity() );
        inventoryItemGetDto.quantityOnHand( inventoryItem.getQuantityOnHand() );
        inventoryItemGetDto.quantityAvailableToPromise( inventoryItem.getQuantityAvailableToPromise() );
        inventoryItemGetDto.comment( inventoryItem.getComment() );
        inventoryItemGetDto.deleted( inventoryItem.getDeleted() );
        inventoryItemGetDto.active( inventoryItem.getActive() );

        return inventoryItemGetDto.build();
    }

    @Override
    public InventoryItem toEntity(InventoryItemPostDto inventoryItemPostDto) {
        if ( inventoryItemPostDto == null ) {
            return null;
        }

        InventoryItem.InventoryItemBuilder<?, ?> inventoryItem = InventoryItem.builder();

        inventoryItem.id( inventoryItemPostDto.id() );
        inventoryItem.product( productService.idToEntity( inventoryItemPostDto.product() ) );
        inventoryItem.inventory( inventoryService.idToEntity( inventoryItemPostDto.inventory() ) );
        inventoryItem.lotNumber( inventoryItemPostDto.lotNumber() );
        inventoryItem.comment( inventoryItemPostDto.comment() );
        inventoryItem.quantity( inventoryItemPostDto.quantity() );
        inventoryItem.quantityOnHand( inventoryItemPostDto.quantityOnHand() );
        inventoryItem.quantityAvailableToPromise( inventoryItemPostDto.quantityAvailableToPromise() );
        inventoryItem.expirationDate( inventoryItemPostDto.expirationDate() );
        if ( inventoryItemPostDto.lotStatus() != null ) {
            inventoryItem.lotStatus( Enum.valueOf( LotStatus.class, inventoryItemPostDto.lotStatus() ) );
        }
        inventoryItem.active( inventoryItemPostDto.active() );
        inventoryItem.deleted( inventoryItemPostDto.deleted() );

        return inventoryItem.build();
    }

    @Override
    public InventoryItem toUpdateEntity(InventoryItemPostDto inventoryItemPostDto, InventoryItem inventoryItem) {
        if ( inventoryItemPostDto == null ) {
            return inventoryItem;
        }

        inventoryItem.setId( inventoryItemPostDto.id() );
        inventoryItem.setProduct( productService.idToEntity( inventoryItemPostDto.product() ) );
        inventoryItem.setInventory( inventoryService.idToEntity( inventoryItemPostDto.inventory() ) );
        inventoryItem.setLotNumber( inventoryItemPostDto.lotNumber() );
        inventoryItem.setComment( inventoryItemPostDto.comment() );
        inventoryItem.setQuantity( inventoryItemPostDto.quantity() );
        inventoryItem.setQuantityOnHand( inventoryItemPostDto.quantityOnHand() );
        inventoryItem.setQuantityAvailableToPromise( inventoryItemPostDto.quantityAvailableToPromise() );
        inventoryItem.setExpirationDate( inventoryItemPostDto.expirationDate() );
        if ( inventoryItemPostDto.lotStatus() != null ) {
            inventoryItem.setLotStatus( Enum.valueOf( LotStatus.class, inventoryItemPostDto.lotStatus() ) );
        }
        else {
            inventoryItem.setLotStatus( null );
        }
        inventoryItem.setActive( inventoryItemPostDto.active() );
        inventoryItem.setDeleted( inventoryItemPostDto.deleted() );

        return inventoryItem;
    }

    @Override
    public InventoryItemSlimDto toSlimDto(InventoryItem inventoryItem) {
        if ( inventoryItem == null ) {
            return null;
        }

        String name = null;
        Long id = null;

        name = inventoryItem.getLotNumber();
        id = inventoryItem.getId();

        InventoryItemSlimDto inventoryItemSlimDto = new InventoryItemSlimDto( id, name );

        return inventoryItemSlimDto;
    }

    @Override
    public InventoryItemListDto toListDto(InventoryItem inventoryItem) {
        if ( inventoryItem == null ) {
            return null;
        }

        String name = null;
        Long id = null;

        name = inventoryItem.getLotNumber();
        id = inventoryItem.getId();

        InventoryItemListDto inventoryItemListDto = new InventoryItemListDto( id, name );

        return inventoryItemListDto;
    }

    private String inventoryItemLotStatusStatus(InventoryItem inventoryItem) {
        if ( inventoryItem == null ) {
            return null;
        }
        LotStatus lotStatus = inventoryItem.getLotStatus();
        if ( lotStatus == null ) {
            return null;
        }
        String status = lotStatus.getStatus();
        if ( status == null ) {
            return null;
        }
        return status;
    }

    protected ProductSlimDto productToProductSlimDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductSlimDto productSlimDto = new ProductSlimDto();

        if ( product.getId() != null ) {
            productSlimDto.setId( product.getId() );
        }
        productSlimDto.setName( product.getName() );

        return productSlimDto;
    }
}
