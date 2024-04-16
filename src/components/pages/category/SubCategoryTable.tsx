'use client'
import constraints from '@/actions/constraints'
import Link from 'next/link'
import { useRouter, useSearchParams } from 'next/navigation'
import React, { useEffect, useState } from 'react'
interface categorySmall {
    smallId: number
    smallName: string
}

interface categoryDetail {
    detailId: number
    detailName: string
}
export default function SubCategoryTable() {
    const searchParams = useSearchParams()
    const largeId = searchParams.get('largeId')
    const middleId = searchParams.get('middleId')
    const smallId = searchParams.get('smallId') || ''
    const detailId = searchParams.get('detailId') || ''
    const [category, setCategory] = useState<categorySmall[]>([] as categorySmall[])
    const [Dcategory, setDCategory] = useState<categoryDetail[]>([] as categoryDetail[])

    const router = useRouter()
    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/small/${middleId}`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }
        getCategory()
    }, [middleId])

    useEffect(() => {
        if (smallId) {
            const getCategory = async () => {
                const data = await fetch(`${constraints.Server_Url}/api/v1/category/detail/${smallId}`)
                if (data) {
                    const response = await data.json()
                    setDCategory(response)
                    console.log(response, 'dfd')
                }
            }
            getCategory()
        }
    }, [smallId, detailId])
    return (
        <section>
            {smallId.length === 0 ? (
                <div className="col-start-2 col-end-auto ms-1 me-1">
                    <div className="grid-cols-3 grid border-t-[1px] border-gray-200 ">
                        {category &&
                            category.map((opt: categorySmall, index) => (
                                <div key={index}>
                                    <Link
                                        href={`productlist?largeId=${largeId}&middleId=${middleId}&smallId=${opt.smallId}`}
                                        className="relative flex text-[11px] items-center text-ellipsis ps-[13px] pe-[13px] h-[46px] overflow-hidden border-b-[1px] border-r-[1px]"
                                    >
                                        <div className="overflow-hidden text-ellipsis">{opt.smallName}</div>
                                    </Link>
                                </div>
                            ))}
                    </div>
                </div>
            ) : (
                <div className="col-start-2 col-end-auto ms-1 me-1">
                    <div className="grid-cols-3 grid border-t-[1px] border-gray-200 ">
                        {Dcategory.length > 0 &&
                            Dcategory.map((opt: categoryDetail, index) => (
                                <div key={index}>
                                    <Link
                                        href={`productlist?largeId=${largeId}&middleId=${middleId}&smallId=${smallId}&detailId=${opt.detailId}`}
                                        className="relative flex text-[11px] items-center text-ellipsis ps-[13px] pe-[13px] h-[46px] overflow-hidden border-b-[1px] border-r-[1px]"
                                    >
                                        <div className="overflow-hidden text-ellipsis">{opt?.detailName}</div>
                                    </Link>
                                </div>
                            ))}
                    </div>
                </div>
            )}
        </section>
    )
}
