import ProductList from '@/components/pages/category/ProductList'
import LargeCategoryListToolBar from '@/components/pages/largeCategory/LargeCategoryListToolBar'

export interface product {
    productId: string
    id: number
}

async function getProductList(largeId: string) {
    const data = await fetch(`https://tyrannoback.com/api/v1/product/productList?largeId=${largeId}`)
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
                <div className="text-xs flex ps-4 pe-4 pt-3">
                    {/* <div className="font-bold">~개</div> */}
                    <div className="text-gray-500">전체보기</div>
                </div>
                <ProductList productListIdData={data} />
            </div>
        </div>
    )
}

export default CategoryProductListPage
