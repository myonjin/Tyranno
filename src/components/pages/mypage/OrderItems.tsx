'use client'

import { getOrderListAPI } from '@/actions/mypage'
import { MypageOrderDataType } from '@/types/OrderDataTypte'
import { use, useEffect, useState } from 'react'
import Image from 'next/image'
export default function OrderItems() {
    const [orderList, setOrderList] = useState([])

    const getOrderList = async () => {
        const res = await getOrderListAPI()
        setOrderList(res)
    }
    useEffect(() => {
        getOrderList()
    }, [])
    const handleDate = (date: string) => {
        return date.split('T')[0]
    }
    return (
        <section className="w-full bg-[#9b9b9b]">
            {orderList.map((order: MypageOrderDataType, index: number) => (
                <div key={index} className="bg-white mb-3" style={{ borderRadius: '10px' }}>
                    <div className="p-4">
                        <div className="py-3">
                            <span className="font-bold text-lg">{handleDate(order.orderDate)}</span>
                            <span className="font-thin text-sm ml-3">{order.orderNumber}</span>
                        </div>
                        <div className="mb-1 text-sm">
                            <span>결제 금액 {order.totalMoney}</span>
                        </div>
                        <hr className="bg-[#9b9b9b] h-[2px]" />
                        <div className="flex justify-between">
                            <span className="font-semibold py-4">택배배송</span>
                            <span className="p-4 font-semibold">{order.receiverName}</span>
                        </div>
                    </div>
                    <div className="w-full h-10 content-center" style={{ backgroundColor: '#d6d2c4' }}>
                        <ul className="table relative w-full table-fixed justify-center text-center">
                            <li className="table-cell relative z-10 w-full text-center text-red-600">결제완료</li>
                            <li className="table-cell relative z-10 w-full text-center">상품준비중</li>
                            <li className="table-cell relative z-10 w-full text-center">배송준비중</li>
                            <li className="table-cell relative z-10 w-full text-center">배송중</li>
                            <li className="table-cell relative z-10 w-full text-center">배송완료</li>
                        </ul>
                    </div>
                    {order.orderDtoList.map((product, idx) => (
                        <div key={idx}>
                            <ul>
                                <li className="flex items-center py-5 px-4 border-t" key={product.orderId}>
                                    <div className="flex items-start relative w-full">
                                        <label className="relative mr-3">
                                            <Image src={product.imageUrl} alt="상품" width={85} height={85} />
                                        </label>
                                        <div className="flex flex-col">
                                            <div className="w-full">
                                                <strong>{product.vendorName}</strong>
                                                <span className="ml-1">{product.productName}</span>
                                            </div>

                                            <div className="flex">
                                                <span className="text-sm mr-4 font-semibold">
                                                    {(
                                                        product.price *
                                                        product.count *
                                                        (1 - product.discount / 100)
                                                    ).toLocaleString()}
                                                    원
                                                </span>
                                                {product.discount > 0 && (
                                                    <span className="text-sm line-through">
                                                        {(product.price * product.count).toLocaleString()}원
                                                    </span>
                                                )}
                                                <span className="text-sm ml-2">{product.count}개</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    ))}
                    {/* <hr className="bg-[#9b9b9b] h-[40px]" /> */}
                </div>
            ))}
        </section>
    )
}
