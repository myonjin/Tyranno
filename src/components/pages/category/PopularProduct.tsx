'use client'
import { productInformation } from '@/types/ProductType'
import Image from 'next/image'
import Link from 'next/link'
import { useEffect, useState } from 'react'
import LikeAndCart from './PopularProductlikecart'
import constraints from '@/actions/constraints'

export default function PopularProduct({ productId, id }: { productId: string; id: number }) {
    const [productInformationData, setProductInformationData] = useState<productInformation>({} as productInformation)
    const [random, setRandom] = useState(0)
    useEffect(() => {
        const getProductData = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/product/productInformation/${productId}`, {
                cache: 'force-cache',
            })
            if (data) {
                const response = await data.json()
                setProductInformationData(response.result)
            }
        }
        getProductData()
    }, [productId])
    useEffect(() => {
        const randomNum = Math.floor(Math.random() * 10 + 1)
        setRandom(randomNum)
    }, [random])
    return (
        <div>
            <div className="relative pt-[0.625rem] pb-5">
                <div className="relative">
                    <Link href={`/product/${productId}`}>
                        <div className="relative">
                            <div className=" overflow-hidden justify-center items-center">
                                <img
                                    src={productInformationData.imageUrl}
                                    alt="상품썸네일"
                                    className="will-change-auto max-w-[100%]"
                                    sizes="100vw"
                                    style={{
                                        width: '100%',
                                        height: 'auto',
                                    }}
                                    width={200}
                                    height={100}
                                />
                            </div>
                        </div>
                    </Link>
                    <div className="flex absolute justify-between pointer-events-none top-0 right-0 left-0">
                        <div className="flex-shrink-0 max-w-[100%] ml-auto">
                            <div className="flex flex-row items-center">
                                <div className="flex items-center align-top h-[1.25rem] pr-[0.25rem] text-[10px] bg-white text-red-600">
                                    ▲{random}
                                    <span className="border-0 h-[1px] w-[1px] -my-[1px] -ml-[1px] -mr-[1px] overflow-hidden text-nowrap absolute p-0 collapse">
                                        순위변동정보
                                    </span>
                                </div>
                                <div className="flex justify-center items-center w-5 h-5 p-[6px] bg-gray-600 text-white text-[11px] font-medium">
                                    {id}
                                    <span className="border-0 h-[1px] w-[1px] -my-[1px] -ml-[1px] -mr[1px] p-0 overflow-hidden absolute">
                                        위
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <LikeAndCart productId={productId} islike={productInformationData.isLiked} />
                <Link href={`/product/${productId}`}>
                    <div className="block mt-[0.625rem] pr-[1.25rem]">
                        <p className="text-ellipsis line-clamp-2 text-sm ">
                            <span className="font-bold">{productInformationData.vendorName}</span>
                            <span className="ml-1">{productInformationData.productName}</span>
                        </p>

                        {productInformationData.discount === null ? (
                            <div className="flex flex-col">
                                <em className="mt-0 -me-0 mb-0 ms-[0.25rem] not-italic font-semibold">
                                    <span className="border-0 w-[1px] h-[1px] -my-px -mx-px px-0 py-0 overflow-hidden whitespace-nowrap absolute">
                                        판매가격
                                    </span>
                                    {productInformationData.price.toLocaleString()}원
                                </em>
                            </div>
                        ) : (
                            <div className="mt-[0.25rem]">
                                <div className="flex flex-col">
                                    <del className="block text-xs text-gray-500">
                                        <span className="border-0 h-[1px] w-[1px] -my-px -mx-px p-0 overflow-hidden absolute whitespace-nowrap">
                                            정상가격
                                        </span>
                                        {productInformationData.price}원
                                    </del>
                                    <div className="mt-[0.125rem] mb-0 flex flex-row">
                                        <em className="block font-semibold text-base not-italic text-red-500">
                                            <span className="border-0 w-[1px] h-[1px] -my-px -mx-px px-0 py-0 overflow-hidden whitespace-nowrap absolute ">
                                                할인율
                                            </span>
                                            {productInformationData.discount}%
                                        </em>
                                        <em className="mt-0 -me-0 mb-0 ms-[0.25rem] font-semibold not-italic">
                                            <span className="border-0 w-[1px] h-[1px] -my-px -mx-px px-0 py-0 overflow-hidden whitespace-nowrap absolute">
                                                판매가격
                                            </span>
                                            {(
                                                productInformationData.price *
                                                (1 - productInformationData.discount / 100)
                                            ).toLocaleString()}
                                            원
                                        </em>
                                    </div>
                                </div>
                            </div>
                        )}
                        <div className="flex flex-row items-center text-xs mt-1 whitespace-nowrap text-gray-400">
                            <div className=" relative h-3 w-3 ">
                                <Image
                                    src="https://img.icons8.com/ios-glyphs/30/000000/star--v1.png"
                                    alt="star--v1"
                                    fill
                                />
                            </div>
                            <p className="mt-0 me-0 mb-0 ms-1">
                                <span className="border-0 w-[1px] h-[1px] -my-px -mx-px px-0 py-0 overflow-hidden whitespace-nowrap absolute">
                                    리뷰 별점
                                </span>
                                {productInformationData.productRate}
                                <span className="border-0 w-[1px] h-[1px] -my-px -mx-px px-0 py-0 overflow-hidden whitespace-nowrap absolute">
                                    점
                                </span>
                            </p>
                            <div className="w-[1px] h-[11px] pr-2"></div>
                            <p>
                                <span className="border-0 w-[1px] h-[1px] -my-px -mx-px px-0 py-0 overflow-hidden whitespace-nowrap absolute">
                                    리뷰 갯수
                                </span>
                                {productInformationData.reviewCount}건
                            </p>
                        </div>
                    </div>
                </Link>
            </div>
        </div>
    )
}
