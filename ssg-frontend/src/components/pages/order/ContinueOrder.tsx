'use client'
import { CartMoneyAtom } from '@/state/CartCheckedListAtom'
import Link from 'next/link'
import { useResetRecoilState } from 'recoil'

export default function ContinueOrder() {
    const resetMoneyRecoil = useResetRecoilState(CartMoneyAtom)
    return (
        <div className="m-3 mt-[30px] h-[44px] mb-32">
            <Link href={'/'}>
                <button
                    className="w-1/2 bg-black text-white text-[14px] px-2 h-full rounded-full"
                    onClick={resetMoneyRecoil}
                >
                    계속 쇼핑하기
                </button>
            </Link>
            <Link href={'/mypage/manage/orderlist'}>
                <button
                    className="w-1/2 text-[14px] text-[#444444] border-[1px] px-2 h-full rounded-full border-[#e5e5e5]"
                    onClick={resetMoneyRecoil}
                >
                    주문상품 상세보기
                </button>
            </Link>
        </div>
    )
}
