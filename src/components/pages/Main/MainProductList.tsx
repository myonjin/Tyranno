import constraints from '@/actions/constraints'
import PopularProduct from '../category/PopularProduct'
import Image from 'next/image'
export interface product {
    productId: string
    id: number
}
async function getProductList() {
    const data = await fetch(`${constraints.Server_Url}/api/v1/product/productList?sortCriterion=3`, {})
    if (data) {
        const response = await data.json()
        return response.result.productIds
    }
}

async function ProductList() {
    const productListIdData: product[] = await getProductList()
    // console.log(productListIdData, '1111')

    // if (productListIdData.length === 0)
    //     return (
    //         <div className="grid-cols-2 md:grid-cols-4 grid gap-y-0 gap-x-5 ms-4 me-4 ">찾으시는 상품이 없습니다.</div>
    //     )

    return (
        <section className="p-4">
            <div className="font-bold text-lg">가장 인기 있는 상품이에요!</div>
            <div className=" relative w-full h-24  mt-2 ">
                <Image
                    src={
                        'https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202310/2023102720473778515326115532_491.png&w=750&h=0'
                    }
                    alt="베스트"
                    fill
                />
            </div>
            <div className="grid-cols-2 md:grid-cols-4 grid gap-y-0 gap-x-3 ms-4 me-4 ">
                {productListIdData.map((item: product, idx) => (
                    <PopularProduct productId={item.productId} id={item.id} key={idx} />
                ))}
            </div>
        </section>
    )
}
export default ProductList
