import constraints from '@/actions/constraints'
import ProductList from '@/components/pages/category/ProductList'
import LargeCategoryListToolBar from '@/components/pages/largeCategory/LargeCategoryListToolBar'

export interface product {
    productId: string
    id: number
}

async function getProductList(largeId: string) {
    const data = await fetch(`${constraints.Server_Url}/api/v1/product/productList?largeId=${largeId}`)
    if (data) {
        const response = await data.json()

        return response.result.productIds
    }
}

async function CategoryProductListPage({ params }: { params: { largeId: string } }) {
    const largeId: string = params.largeId
    const data: product[] = await getProductList(largeId)

    return (
        <div className="min-h-screen">
            <div className="contents">
                <LargeCategoryListToolBar largeId={largeId} />
            </div>
            <div className="col-start-2 col-end-auto">
                <ProductList productListIdData={data} />
            </div>
        </div>
    )
}

export default CategoryProductListPage
