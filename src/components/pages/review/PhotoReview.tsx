'use client'
import Link from 'next/link'
import React, { useState } from 'react'
import Image from 'next/image'

export default function PhotoReview({ openModal }: { openModal: any }) {
    return (
        <div className="overflow-hidden">
            <div className="overflow-x-auto whitespace-nowrap flex flex-nowrap gap-2 mx-[-20px] px-[20px]">
                {' '}
                <Image
                    src={'https://img.icons8.com/clouds/100/facebook-like.png'}
                    alt="이미지"
                    width={100}
                    height={100}
                />
            </div>
        </div>
    )
}
