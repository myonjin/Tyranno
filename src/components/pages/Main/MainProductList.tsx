'use client'
import { useEffect, useState } from 'react'
import PopularProduct from '../category/PopularProduct'

export interface product {
    productId: string
    id: number
}

function ProductList({ middleId }: { middleId: number }) {
    const [productListIdData, setPoductListIdData] = useState<product[]>([] as product[])
    useEffect(() => {
        const getProduct = async () => {
            const data = await fetch(`https://tyrannoback.com/api/v1/product/productList?middleId=1`)
            if (data) {
                const response = await data.json()
                setPoductListIdData(response.result.productIds)
            }
        }
        getProduct()
    }, [])
    useEffect(() => {
        return () => {
            setPoductListIdData([])
        }
    }, [])
    console.log(productListIdData, '1111')

    // if (productListIdData.length === 0)
    //     return (
    //         <div className="grid-cols-2 md:grid-cols-4 grid gap-y-0 gap-x-5 ms-4 me-4 ">찾으시는 상품이 없습니다.</div>
    //     )
    // console.log(productListIdData)

    return (
        <div className="grid-cols-2 md:grid-cols-4 grid gap-y-0 gap-x-5 ms-4 me-4 ">
            {productListIdData.map((item: product, idx) => (
                <PopularProduct productId={item.productId} key={idx} />
            ))}
        </div>
    )
}
export default ProductList
