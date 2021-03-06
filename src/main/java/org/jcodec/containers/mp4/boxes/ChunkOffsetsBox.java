package org.jcodec.containers.mp4.boxes;

import java.nio.ByteBuffer;

/**
 * This class is part of JCodec ( www.jcodec.org )
 * This software is distributed under FreeBSD License
 * 
 * A box to hold chunk offsets
 * 
 * @author The JCodec project
 * 
 */

public class ChunkOffsetsBox extends FullBox {
    private long[] chunkOffsets;
    
    public static String fourcc() {
        return "stco";
    }

    public ChunkOffsetsBox(long[] chunkOffsets) {
        super(new Header(fourcc()));
        this.chunkOffsets = chunkOffsets;
    }

    public ChunkOffsetsBox() {
        super(new Header(fourcc()));
    }

    public void parse(ByteBuffer input) {
        super.parse(input);
        int length = input.getInt();
        chunkOffsets = new long[length];
        for (int i = 0; i < length; i++) {
            chunkOffsets[i] = input.getInt() & 0xffffffffL;
        }
    }

    @Override
    public void doWrite(ByteBuffer out) {
        super.doWrite(out);
        out.putInt(chunkOffsets.length);
        for (long offset : chunkOffsets) {
            out.putInt((int) offset);
        }
    }

    public long[] getChunkOffsets() {
        return chunkOffsets;
    }

    public void setChunkOffsets(long[] chunkOffsets) {
        this.chunkOffsets = chunkOffsets;
    }
}
