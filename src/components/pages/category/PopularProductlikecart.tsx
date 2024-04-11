'use client'
import Image from 'next/image'
import { useState } from 'react'
import HeartIcon from '@/images/HeartIcon.png'
import RedHeartIcon from '@/images/RedHeartIcon.png'
function LikeAndCart() {
    const [like, setLike] = useState<number>(99)
    const handleLike = (islike: number) => {
        setLike(islike)
    }
    return (
        <div className=" flex items-center pt-[0.125rem] pb-[0.125rem] ">
            {/* <p className="text-xs">{store === null ? '' : store}</p> */}
            <div className="flex-grow flex-shrink basis-[0%] self-stretch justify-self-stretch"></div>

            {like === 99 ? (
                <button onClick={() => handleLike(11)} className=" flex justify-center items-center  w-[20px] h-[20px]">
                    <Image src={HeartIcon} alt="안좋아요"></Image>
                </button>
            ) : (
                <button onClick={() => handleLike(99)} className=" flex justify-center items-center w-[20px] h-[20px]">
                    <Image src={RedHeartIcon} alt="좋아요" />
                </button>
            )}
            <button className="inline-flex justify-center items-center w-7 h-7 ml-1">
                <Image
                    width="24"
                    height="48"
                    src="https://img.icons8.com/parakeet-line/48/shopping-cart.png"
                    alt="장바구니"
                />
            </button>
        </div>
    )
}
export default LikeAndCart
