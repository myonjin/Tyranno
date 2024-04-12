'use client'
import Image from 'next/image'
import { useEffect, useState } from 'react'
import HeartIcon from '@/images/HeartIcon.png'
import RedHeartIcon from '@/images/RedHeartIcon.png'
import { LikeClickAPI, getLikeAPI, getProductListAPI } from '@/actions/product'
import { LIKEType } from '@/types/LikeType'
import { useRouter } from 'next/navigation'

function LikeAndCart({ productId, islike }: { productId: string; islike: number }) {
    const [like, setLike] = useState<number>(99)

    const handleLike = async () => {
        const body: LIKEType = {
            productId: productId,
        }
        const res = await LikeClickAPI(body)
        const response = await getLikeAPI(productId)
        setLike(response.result)

        // setLike(response)
    }
    useEffect(() => {
        const getLike = async () => {
            const response = await getLikeAPI(productId)
            setLike(response.result)
        }
        getLike()
    }, [productId])
    const router = useRouter()
    const handleNonUser = async () => {
        router.push(`/user/login`)
    }

    return (
        <div className=" flex items-center pt-[0.125rem] pb-[0.125rem] ">
            <div className="flex-grow flex-shrink basis-[0%] self-stretch justify-self-stretch"></div>

            {like === 99 ? (
                <button onClick={() => handleLike()} className=" flex justify-center items-center  w-[20px] h-[20px]">
                    <Image src={HeartIcon} alt="안좋아요"></Image>
                </button>
            ) : like === 11 ? (
                <button onClick={() => handleLike()} className=" flex justify-center items-center w-[20px] h-[20px]">
                    <Image src={RedHeartIcon} alt="좋아요" />
                </button>
            ) : like === null ? (
                <button
                    onClick={() => handleNonUser()}
                    className=" flex justify-center items-center  w-[20px] h-[20px]"
                >
                    <Image src={HeartIcon} alt="안좋아요"></Image>
                </button>
            ) : (
                ''
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
