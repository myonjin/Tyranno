'use client'
import Image from 'next/image'
import React, { useEffect, useState, useRef } from 'react'
import MainProductList from './MainProductList'
import constraints from '@/actions/constraints'

interface categoryMiddle {
    middleId: number
    middleName: string
}

export default function SubCategorySlideButton() {
    const [category, setCategory] = useState<categoryMiddle[]>([] as categoryMiddle[])
    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/middle/1`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }
        getCategory()
    }, [])
    // console.log(category)

    return (
        <div className="col-start-2 col-end-auto ms-[(1rem)*-1] me-[(1rem)*-1] top-[46px] bg-white">
            <div className="flex-start flex-shrink-0 align-middle relative pr-[54px]">
                <div className="h-[56px] overflow-hidden text-nowrap flex">
                    <div className="flex-nowrap pt-[10px] pb-[10px] ps-3 pe-1  overflow-x-scroll">
                        {category &&
                            category.map((opt: categoryMiddle, index) => (
                                <button
                                    key={index}
                                    className="min-w-min h-[36px] text-xs font-semibold mr-[5px] pl-2 pr-2 bg-gray-100  text-black"
                                >
                                    <div> {opt.middleName}</div>
                                    {/* <MainProductList /> */}
                                </button>
                            ))}
                    </div>

                    <div className="bg-white top-[10px] absolute bottom-[10px] right-0 pr-4">
                        <button
                            // onClick={handleOpenAllCategory}
                            className="min-w-9 min-h-9 rotate-90 inline-flex items-center justify-center text-sm border border-gray-200"
                        >
                            <div className="relative w-5 h-5 text-black font-bold">
                                <Image
                                    src="https://img.icons8.com/sf-ultralight/25/000000/back.png"
                                    alt="back"
                                    style={{ transform: 'rotate(180deg)' }}
                                    fill
                                />
                            </div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}
