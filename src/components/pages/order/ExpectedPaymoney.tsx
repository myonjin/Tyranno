'use client'
import { CartMoneyAtom } from '@/state/CartCheckedListAtom'
import { SelectedOptionItemListAtom } from '@/state/SelectedOptionListAtom'
import { useRecoilValue } from 'recoil'

export default function ExpectedPaymoney() {
    const [money,setMoney] = useRecoilValue(SelectedOptionItemListAtom)
    console.log(money,'결제예정')
   
    return (
        <>
            <div className="bg-white m-4 p-4 rounded-xl tracking-[-0.3px]">
                <div className="flex justify-between text-lg mb-2 font-extrabold">
                    <span>결제예정금액</span>
                    <span>{(money.price + money.discount + 3000).toLocaleString()}원</span>
                </div>
                <hr className="bg-[#9b9b9b] h-[0.3px]" />
                <div className="mt-2 text-sm">
                    <div className="flex justify-between">
                        <span>주문금액</span>
                        <span>{money.price.toLocaleString()}원</span>
                    </div>
                    <div className="flex justify-between my-1">
                        <span>배송비</span>
                        <span>+ {'3,000'}원</span>
                    </div>
                    <div className="flex justify-between my-1">
                        <span>할인금액</span>
                        <span className="text-[red]">{money.discount.toLocaleString()}원</span>
                    </div>
                    <div className="flex justify-between text-[#888888]">
                        <span className="">└ 상품할인</span>
                        <span> {money.discount.toLocaleString()}원</span>
                    </div>
                </div>
            </div>
        </>
    )
}
