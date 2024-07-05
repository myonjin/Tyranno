import ProductList from '@/components/pages/category/ProductList'
import SubCategorySlideButton from '@/components/pages/category/SubCategorySlideButton'
import CategoryProductListToolBar from '@/components/pages/category/CategoryProductListToolBar'
import SubCategoryTable from '@/components/pages/category/SubCategoryTable'
import constraints from '@/actions/constraints'

export interface product {
    productId: string
    id: number
}

async function getProductList(
    largeId: string,
    middleId: string,
    smallId: string,
    detailId: string,
    sortCriterion: string,
) {
    const data = await fetch(
        `${constraints.Server_Url}/api/v1/product/productList?largeId=${largeId}&middleId=${middleId}&smallId=${smallId}&detailId=${detailId}&sortCriterion=${sortCriterion}`,
    )
    if (data) {
        const response = await data.json()

        return response.result.productIds
    }
}

async function CategoryProductListPage({ searchParams }: { searchParams: { [key: string]: string } }) {
    const params = searchParams
    const smallId = params.smallId || ''
    const detailId = params.detailId || ''
    const sortCriterion = params.sortCriterion || ''

    const productListIdData: product[] = await getProductList(
        params.largeId,
        params.middleId,
        smallId,
        detailId,
        sortCriterion,
    )

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
