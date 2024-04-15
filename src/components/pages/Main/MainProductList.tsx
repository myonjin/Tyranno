'use client'
import constraints from '@/actions/constraints'
import PopularProduct from '../category/PopularProduct'
import Image from 'next/image'
import { useEffect, useRef, useState } from 'react'

export interface productList {
    productIds: []
    totalCount: number
    lastPage: number
    currentPage: number
}
export interface productIds {
    productId: string
    id: number
}

function ProductList() {
    const [currentPage, setCurrentPage] = useState<number>(1)
    const [productListIdData, setProductListIdData] = useState<productIds[]>([])
    const loader = useRef(null)

    useEffect(() => {
        const GetProductList = async () => {
            const data = await fetch(
                `${constraints.Server_Url}/api/v1/product/productList?sortCriterion=3&page=${currentPage}`,
            )
            if (data) {
                const response = await data.json()
                setProductListIdData((prev) => [...prev, ...response.result.productIds])
            }
        }
        GetProductList()
    }, [currentPage])

    useEffect(() => {
        const observer = new IntersectionObserver(handleObserver, { threshold: 1 })
        if (loader.current) observer.observe(loader.current)
    }, [])

    const handleObserver = (entities: any) => {
        const target = entities[0]
        if (target.isIntersecting) {
            setCurrentPage((prev) => prev + 1)
        }
    }

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
                {productListIdData.map((item: productIds, idx) => (
                    <PopularProduct productId={item.productId} id={item.id} key={idx} />
                ))}
            </div>
            <div ref={loader} style={{ height: '100px', margin: '30px' }}>
                <span>로딩 중...</span>
            </div>
        </section>
    )
}
export default ProductList
