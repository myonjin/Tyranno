'use client'

import Link from 'next/link'
import { useSearchParams } from 'next/navigation'
import React, { useEffect, useState } from 'react'
interface categorySmall {
    smallId: number
    smallName: string
}
export default function SubCategoryTable() {
    const searchParams = useSearchParams()
    const largeId = searchParams.get('largeId')
    const middleId = searchParams.get('middleId')
    const [category, setCategory] = useState<categorySmall[]>([] as categorySmall[])
    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`https://tyrannoback.com/api/v1/category/small/${middleId}`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }
        getCategory()
    }, [middleId])
    return (
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
    )
}
