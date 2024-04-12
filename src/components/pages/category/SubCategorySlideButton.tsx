'use client'
import Image from 'next/image'
// import SmallArrowIcon from '@/images/svgs/SmallArrowIcon';
import React, { useEffect, useState, useRef } from 'react'
import { useRouter, useSearchParams } from 'next/navigation'

interface categoryMiddle {
    middleId: number
    middleName: string
}

export default function SubCategorySlideButton() {
    const searchParams = useSearchParams()
    const largeId = searchParams.get('largeId')
    const middleId = searchParams.get('middleId')
    const [category, setCategory] = useState<categoryMiddle[]>([] as categoryMiddle[])
    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`https://tyrannoback.com/api/v1/category/middle/${largeId}`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }
        getCategory()
    }, [largeId])

    // console.log(category)
    // 카테고리 페이지에서 선택한 중분류 카테고리 값이 주어지면 됩니다.
    // const [isSelected, setIsSelected] = useState();

    const buttonRefs = useRef<HTMLButtonElement[]>([])

    /** useRef를 사용하여 선택된 값이 중앙으로 오도록 설정
  const handleCategoryClick = (category: number, index: number) => {
    setIsSelected(category);
    buttonRefs.current[index].scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'center' });
  }*/

    /** 전체 카테고리 모달 관련 state와 함수입니다. 
      당장 적용하기에는 조금 수정이 필요할 거 같아서 추가하고 수정해서 알려드릴게요.
    const [isOpenAllCategory, setIsOpenAllCategory] = useState(false);

    const handleOpenAllCategory = () => {
      setIsOpenAllCategory(true);
     };

     const handleCloseAllCategoryList = () => {
       setIsOpenAllCategory(false);
     };
  */
    const router = useRouter()
    const handleClick = (middleId: number) => {
        router.push(`/productlist?largeId=${largeId}&middleId=${middleId}`)
    }

    return (
        <div className="col-start-2 col-end-auto ms-[(1rem)*-1] me-[(1rem)*-1] top-[46px] bg-white">
            <div className="flex-start flex-shrink-0 align-middle relative ">
                <div className="h-[56px] overflow-hidden text-nowrap flex">
                    <div className="flex-nowrap pt-[10px] pb-[10px] ps-3 pe-1  overflow-x-scroll">
                        {category &&
                            category.map((opt: categoryMiddle, index) => (
                                <button
                                    key={index}
                                    onClick={() => handleClick(opt.middleId)}
                                    className={
                                        'min-w-min h-[36px] text-xs font-semibold mr-[5px] pl-2 pr-2 ' +
                                        (opt.middleId == parseInt(`${middleId}`)
                                            ? ' bg-black text-white border-black'
                                            : 'bg-gray-100 text-black')
                                    }
                                >
                                    <div> {opt.middleName}</div>
                                </button>
                            ))}
                    </div>
                </div>
            </div>
        </div>
    )
}
