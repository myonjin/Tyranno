import ProductList from '@/components/pages/category/ProductList'
import SubCategorySlideButton from '@/components/pages/category/SubCategorySlideButton'
import CategoryProductListToolBar from '@/components/pages/category/CategoryProductListToolBar'
import SubCategoryTable from '@/components/pages/category/SubCategoryTable'

export interface product {
    productId: string
    id: number
}

async function getProductList(largeId: string, middleId: string) {
    const data = await fetch(
        `https://tyrannoback.com/api/v1/product/productList?largeId=${largeId}&middleId=${middleId}`,
        {
            cache: 'force-cache',
        },
    )
    if (data) {
        const response = await data.json()
        // console.log(response.result.productIds)
        return response.result.productIds
    }
}

async function CategoryProductListPage({ searchParams }: { searchParams: { [key: string]: string } }) {
    const params = searchParams
    const productListIdData: product[] = await getProductList(params.largeId, params.middleId)

    return (
        <div className="min-h-screen">
            <div className="contents">
                <CategoryProductListToolBar />
                <SubCategorySlideButton />
                <SubCategoryTable />
            </div>
            <div className="col-start-2 col-end-auto">
                
                <ProductList productListIdData={productListIdData} />
            </div>
        </div>
    )
}

export default CategoryProductListPage
