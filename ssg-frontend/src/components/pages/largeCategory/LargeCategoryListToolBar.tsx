'use client'
import Image from 'next/image'
import Link from 'next/link'
import React, { useEffect, useState } from 'react'
import { useRouter } from 'next/navigation'
import constraints from '@/actions/constraints'

interface category {
    largeId: number
    largeName: string
}
export default function CategoryProductListToolBar({ largeId }: { largeId: string }) {
    const [Lcategory, setLCategory] = useState<category>({} as category)
    const router = useRouter()

    useEffect(() => {
        const getCategory1 = async () => {
            const data1 = await fetch(`${constraints.Server_Url}/api/v1/category/`)
            if (data1) {
                const response = await data1.json()
                setLCategory(response[parseInt(`${largeId}`) - 1])
            }
        }
        getCategory1()
    }, [])

    return (
        <div className="flex flex-row w-full h-[46px] bg-white items-center pl-3 pr-3 sticky top-0 z-10">
            <div className="items-center h-full">
                <Link
                    href="#"
                    className="h-full flex flex-wrap justify-center items-center  "
                    onClick={(e) => {
                        e.preventDefault()
                        router.back()
                    }}
                >
                    <span className="w-[1px] h-[1px] -mx-[1px] -my-[1px] p-0 overflow-hidden text-nowrap absolute">
                        이전 페이지
                    </span>
                    <div className=" relative  w-6  h-7">
                        <Image src="https://img.icons8.com/ios/50/left--v1.png" alt="뒤로가기" fill />
                    </div>
                </Link>
            </div>
            <div className="pl-5 pr-3 items-center flex ">
                <div className="inline-flex flex-wrap content-center">
                    <p className="text-gray-600 text-sm text-ellipsis">{Lcategory.largeName}</p>
                </div>
                <div className="w-4 h-4 relative  ">
                    <Image
                        src="https://img.icons8.com/sf-ultralight/25/000000/back.png"
                        alt="back"
                        style={{ transform: 'rotate(180deg)' }}
                        fill
                    />
                </div>
                <button className="inline-flex h-8 justify-center items-center">
                    <div className="text-sm font-bold overflow-hidden text-ellipsis">
                        <div>전체보기</div>
                    </div>
                </button>
            </div>
            <div className="flex-grow flex-shrink basis-0 justify-stretch self-stretch"></div>
            <div className="w-8 h-8 flex justify-center items-center flex-grow-0 flex-shrink-0 basis-auto">
                <button className="flex justify-center items-center">
                    <div className=" relative w-6 h-6 inline-block flex-shrink-0 align-middle">
                        <Image src="https://img.icons8.com/windows/32/like--v1.png" alt="좋아요" fill />
                    </div>
                </button>
            </div>
            <div className="w-8 h-8 flex justify-center items-center flex-grow-0 flex-shrink-0 basis-auto">
                <button className="flex">
                    <div className="  relative w-6 h-6 inline-block flex-shrink-0 align-middle">
                        <Image
                            src="https://img.icons8.com/fluency-systems-regular/48/share--v1.png"
                            alt="공유하기"
                            fill
                        />
                    </div>
                </button>
            </div>
        </div>
    )
}
