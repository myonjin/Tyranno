'use client'
import { CartMoneyAtom } from '@/state/CartCheckedListAtom'
import { useRecoilValue } from 'recoil'

export default function ExpectedPaymoney() {
    const money = useRecoilValue(CartMoneyAtom)

    return (
        <>
            <div className="bg-white m-4 p-4 rounded-xl tracking-[-0.3px]">
                <div className="flex justify-between text-lg mb-2 font-extrabold">
                    <span>결제예정금액</span>
                    <span>
                        {money !== undefined
                            ? Math.floor(money.orderMoney + money.discountMoney + 3000).toLocaleString()
                            : 0}
                        원
                    </span>
                </div>
                <hr className="bg-[#9b9b9b] h-[0.3px]" />
                <div className="mt-2 text-sm">
                    <div className="flex justify-between">
                        <span>주문금액</span>
                        <span>{money !== undefined ? (money.orderMoney + 0).toLocaleString() : 0}원</span>
                    </div>
                    <div className="flex justify-between my-1">
                        <span>배송비</span>
                        <span>+ {'3,000'}원</span>
                    </div>
                    <div className="flex justify-between my-1">
                        <span>할인금액</span>
                        <span className="text-[red]">
                            {money !== undefined ? Math.floor(money.discountMoney + 0).toLocaleString() : 0}원
                        </span>
                    </div>
                    <div className="flex justify-between text-[#888888]">
                        <span className="">└ 상품할인</span>
                        <span>{money !== undefined ? Math.floor(money.discountMoney + 0).toLocaleString() : 0}원</span>
                    </div>
                </div>
            </div>
        </>
    )
}
