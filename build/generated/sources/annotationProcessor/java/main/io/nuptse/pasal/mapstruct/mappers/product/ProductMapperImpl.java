package io.nuptse.pasal.mapstruct.mappers.product;

import io.nuptse.pasal.mapstruct.dto.product.ProductGetDto;
import io.nuptse.pasal.mapstruct.dto.product.ProductListDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.AttributeSlimDto;
import io.nuptse.pasal.mapstruct.dto.product.category.CategorySlimDto;
import io.nuptse.pasal.mapstruct.dto.vendor.VendorSlimDto;
import io.nuptse.pasal.models.Vendor;
import io.nuptse.pasal.models.product.Attribute;
import io.nuptse.pasal.models.product.Category;
import io.nuptse.pasal.models.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:05+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public ProductGetDto productToProductGetDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductGetDto.ProductGetDtoBuilder productGetDto = ProductGetDto.builder();

        productGetDto.categorySlimDto( categoryListToCategorySlimDtoList( product.getCategories() ) );
        productGetDto.attributeSlimDto( attributeListToAttributeSlimDtoList( product.getAttributes() ) );
        productGetDto.id( product.getId() );
        productGetDto.name( product.getName() );
        productGetDto.description( product.getDescription() );
        productGetDto.pricePerUnit( product.getPricePerUnit() );
        productGetDto.costPerUnit( product.getCostPerUnit() );
        productGetDto.unitOfMeasure( product.getUnitOfMeasure() );
        productGetDto.upc( product.getUpc() );
        productGetDto.vendor( vendorToVendorSlimDto( product.getVendor() ) );
        productGetDto.deleted( product.getDeleted() );
        productGetDto.active( product.getActive() );
        productGetDto.updatedAt( product.getUpdatedAt() );

        return productGetDto.build();
    }

    @Override
    public ProductListDto productToProductListDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductListDto.ProductListDtoBuilder productListDto = ProductListDto.builder();

        if ( product.getId() != null ) {
            productListDto.id( product.getId().intValue() );
        }
        productListDto.name( product.getName() );

        return productListDto.build();
    }

    protected CategorySlimDto categoryToCategorySlimDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategorySlimDto categorySlimDto = new CategorySlimDto();

        if ( category.getId() != null ) {
            categorySlimDto.setId( category.getId() );
        }
        categorySlimDto.setName( category.getName() );

        return categorySlimDto;
    }

    protected List<CategorySlimDto> categoryListToCategorySlimDtoList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategorySlimDto> list1 = new ArrayList<CategorySlimDto>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategorySlimDto( category ) );
        }

        return list1;
    }

    protected List<AttributeSlimDto> attributeListToAttributeSlimDtoList(List<Attribute> list) {
        if ( list == null ) {
            return null;
        }

        List<AttributeSlimDto> list1 = new ArrayList<AttributeSlimDto>( list.size() );
        for ( Attribute attribute : list ) {
            list1.add( attributeMapper.toAttributeSlimDto( attribute ) );
        }

        return list1;
    }

    protected VendorSlimDto vendorToVendorSlimDto(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorSlimDto vendorSlimDto = new VendorSlimDto();

        if ( vendor.getId() != null ) {
            vendorSlimDto.setId( vendor.getId() );
        }
        vendorSlimDto.setName( vendor.getName() );

        return vendorSlimDto;
    }
}
