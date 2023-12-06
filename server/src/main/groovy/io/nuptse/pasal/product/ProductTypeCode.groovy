package io.nuptse.pasal.product

enum ProductTypeCode {

    GOOD(0),
    SERVICE(1),
    FIXED_ASSET(2),
    ASSET_USAGE(3);

    int sortOrder

    ProductTypeCode(int sortOrder) { [this.sortOrder = sortOrder] }

    static int compare(ProductTypeCode a, ProductTypeCode b) {
        return a.sortOrder <=> b.sortOrder
    }

    static list() {
        [GOOD, SERVICE, FIXED_ASSET, ASSET_USAGE]
    }


    String getName() { return name() }

    String toString() { return name() }

}