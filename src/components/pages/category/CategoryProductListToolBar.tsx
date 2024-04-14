'use client'
import Image from 'next/image'
import Link from 'next/link'
import React, { useEffect, useState } from 'react'
import { useRouter, useSearchParams } from 'next/navigation'
import CategoryListModal from '@/components/pages/category/CategoryListModal'
import constraints from '@/actions/constraints'
interface categorySmall {
    smallId: number
    smallName: string
}
interface categoryMiddle {
    middleId: number
    middleName: string
}
interface category {
    largeId: number
    largeName: string
}
export default function CategoryProductListToolBar() {
    const [isOpenModal, setIsOpenModal] = useState(false)
    const [category, setCategory] = useState<categoryMiddle[]>([] as categoryMiddle[])
    const [Lcategory, setLCategory] = useState<category>({} as category)
    const [Scategory, setSCategory] = useState<categorySmall[]>([] as categorySmall[])
    const router = useRouter()
    const searchParams = useSearchParams()
    const largeId = searchParams.get('largeId')
    const middleId = searchParams.get('middleId')
    const smallId = searchParams.get('smallId') || ''
    // console.log(smallId.length, '스몰있는가')
    // 대분류
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
    //중분류
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
    //소분류
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
    // console.log(Scategory, '카테고리명')

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
            {smallId.length === 0 ? (
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
                    <button
                        onClick={() => setIsOpenModal(!isOpenModal)}
                        className="inline-flex h-8 justify-center items-center"
                    >
                        {category &&
                            category.map((opt: categoryMiddle, index) => (
                                <div key={index} className="text-sm font-bold overflow-hidden text-ellipsis">
                                    {opt.middleId === parseInt(`${middleId}`) && <div>{opt.middleName}</div>}
                                </div>
                            ))}

                        <div className={` relative w-3 h-3 inline-block ml-1 ${isOpenModal ? 'rotate-180' : ''}`}>
                            <Image src="https://img.icons8.com/material-sharp/24/give-way--v2.png" alt="더보기" fill />
                        </div>
                    </button>
                </div>
            ) : (
                <div className="pl-5 pr-3 items-center flex ">
                    <div className="inline-flex flex-wrap content-center">
                        {category &&
                            category.map((opt: categoryMiddle, index) => (
                                <div key={index} className="text-sm font-bold overflow-hidden text-ellipsis">
                                    {opt.middleId === parseInt(`${middleId}`) && <div>{opt.middleName}</div>}
                                </div>
                            ))}
                    </div>
                    <div className="w-4 h-4 relative  ">
                        <Image
                            src="https://img.icons8.com/sf-ultralight/25/000000/back.png"
                            alt="back"
                            style={{ transform: 'rotate(180deg)' }}
                            fill
                        />
                    </div>
                    <button
                        onClick={() => setIsOpenModal(!isOpenModal)}
                        className="inline-flex h-8 justify-center items-center"
                    >
                        {Scategory &&
                            Scategory.map((opt: categorySmall, index) => (
                                <div key={index} className="text-gray-600 text-sm text-ellipsis">
                                    {opt.smallId === parseInt(`${smallId}`) && <div>{opt.smallName}</div>}
                                </div>
                            ))}

                        <div className={` relative w-3 h-3 inline-block ml-1 ${isOpenModal ? 'rotate-180' : ''}`}>
                            <Image src="https://img.icons8.com/material-sharp/24/give-way--v2.png" alt="더보기" fill />
                        </div>
                    </button>
                </div>
            )}

            <div className="flex-grow   justify-stretch self-stretch"></div>
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
            {isOpenModal && <CategoryListModal />}
        </div>
    )
}
