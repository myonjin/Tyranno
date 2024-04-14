'use client'
import React, { useEffect, useState } from 'react'
import { useRouter, useSearchParams } from 'next/navigation'
import constraints from '@/actions/constraints'

interface categoryMiddle {
    middleId: number
    middleName: string
}
interface categorySmall {
    smallId: number
    smallName: string
}
export default function SubCategorySlideButton() {
    const searchParams = useSearchParams()
    const largeId = searchParams.get('largeId')
    const middleId = searchParams.get('middleId')
    const smallId = searchParams.get('smallId') || ''
    const [category, setCategory] = useState<categoryMiddle[]>([] as categoryMiddle[])
    const [Scategory, setSCategory] = useState<categorySmall[]>([] as categorySmall[])
    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/middle/${largeId}`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }
        getCategory()
    }, [largeId])
    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/small/${middleId}`)
            if (data) {
                const response = await data.json()
                setSCategory(response)
            }
        }
        getCategory()
    }, [middleId, smallId])

    const router = useRouter()
    const handleClick = (middleId: number) => {
        router.push(`/productlist?largeId=${largeId}&middleId=${middleId}`)
    }
    const handleSmallClick = (smallId: number) => {
        router.push(`/productlist?largeId=${largeId}&middleId=${middleId}&smallId=${smallId}`)
    }

    return (
        <section>
            {smallId.length === 0 ? (
                <div className="col-start-2 col-end-auto ms-[(1rem)*-1] me-[(1rem)*-1] top-[46px] bg-white">
                    <div className="flex-start flex-shrink-0 align-middle relative ">
                        <div className="h-[56px] overflow-hidden text-nowrap flex">
                            <div className="flex-nowrap pt-[10px] pb-[10px] ps-3 pe-1  overflow-x-scroll">
                                {category &&
                                    category.map((opt: categoryMiddle, index) => (
                                        <button
                                            key={index}
                                            onClick={() => handleClick(opt.middleId)}
                                            className={
                                                'min-w-min h-[36px] text-xs font-semibold mr-[5px] pl-2 pr-2 ' +
                                                (opt.middleId == parseInt(`${middleId}`)
                                                    ? ' bg-black text-white border-black'
                                                    : 'bg-gray-100 text-black')
                                            }
                                        >
                                            <div> {opt.middleName}</div>
                                        </button>
                                    ))}
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
                <div className="col-start-2 col-end-auto ms-[(1rem)*-1] me-[(1rem)*-1] top-[46px] bg-white">
                    <div className="flex-start flex-shrink-0 align-middle relative ">
                        <div className="h-[56px] overflow-hidden text-nowrap flex">
                            <div className="flex-nowrap pt-[10px] pb-[10px] ps-3 pe-1  overflow-x-scroll">
                                {Scategory &&
                                    Scategory.map((opt: categorySmall, index) => (
                                        <button
                                            key={index}
                                            onClick={() => handleSmallClick(opt.smallId)}
                                            className={
                                                'min-w-min h-[36px] text-xs font-semibold mr-[5px] pl-2 pr-2 ' +
                                                (opt.smallId == parseInt(`${smallId}`)
                                                    ? ' bg-black text-white border-black'
                                                    : 'bg-gray-100 text-black')
                                            }
                                        >
                                            <div> {opt.smallName}</div>
                                        </button>
                                    ))}
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </section>
    )
}
