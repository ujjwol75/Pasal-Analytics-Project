package io.nuptse.pasal.mapstruct.mappers.product;

import io.nuptse.pasal.mapstruct.dto.product.category.CategoryGetDto;
import io.nuptse.pasal.mapstruct.dto.product.category.CategoryListDto;
import io.nuptse.pasal.mapstruct.dto.product.category.CategoryPostDto;
import io.nuptse.pasal.mapstruct.dto.product.category.CategorySlimDto;
import io.nuptse.pasal.models.product.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryGetDto categoryToCategoryGetDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryGetDto.CategoryGetDtoBuilder categoryGetDto = CategoryGetDto.builder();

        categoryGetDto.parentCategory( categoryToCategorySlimDto( category.getParentCategory() ) );
        categoryGetDto.id( category.getId() );
        categoryGetDto.name( category.getName() );
        categoryGetDto.description( category.getDescription() );

        return categoryGetDto.build();
    }

    @Override
    public CategoryPostDto categoryToCategoryPostDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryPostDto categoryPostDto = new CategoryPostDto();

        categoryPostDto.setId( category.getId() );
        categoryPostDto.setName( category.getName() );
        categoryPostDto.setDescription( category.getDescription() );

        return categoryPostDto;
    }

    @Override
    public Category categoryPostDtoToCategory(CategoryPostDto categoryPostDto) {
        if ( categoryPostDto == null ) {
            return null;
        }

        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.id( categoryPostDto.getId() );
        category.name( categoryPostDto.getName() );
        category.description( categoryPostDto.getDescription() );

        category.parentCategory( mapParentCategory(categoryPostDto.getParentCategory()) );

        return category.build();
    }

    @Override
    public CategoryListDto categoryToCategoryListDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryListDto.CategoryListDtoBuilder categoryListDto = CategoryListDto.builder();

        categoryListDto.id( category.getId() );
        categoryListDto.name( category.getName() );

        return categoryListDto.build();
    }

    @Override
    public List<CategoryPostDto> categoriesToCategoryAllDtos(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryPostDto> list = new ArrayList<CategoryPostDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryPostDto( category ) );
        }

        return list;
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
}
